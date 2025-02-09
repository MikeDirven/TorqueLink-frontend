package nl.torquelink.data.network.result

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess

suspend inline fun <reified T> HttpResponse.decode(
    noinline builder: (DecoderConfig<T>.() -> Unit)? = null
) : Result<T> {
    val config : DecoderConfig<T> = object : DecoderConfig<T> {
        override val statusCodeOverrides: MutableMap<HttpStatusCode, HttpResponse.() -> Result<T>>
                = mutableMapOf()
    }.apply {
        builder?.let { it(this) }
    }

    try {
        // Override handler
        return config.statusCodeOverrides[this.status]
            ?.invoke(this)
            // Default success and error handler
            ?: if(this.status.isSuccess()) {
                val responseBody = try {
                    this.body<T>()
                } catch (e: Exception){
                    null
                }
                responseBody?.let {
                    SuccessResult.WithBody(it)
                } ?: SuccessResult.EmptyResult()
            } else {
                val responseBody = try {
                    this.bodyAsText()
                } catch (e: Exception){
                    null
                }
                responseBody?.let {
                    ErrorResult.Error(Exception(it))
                } ?: ErrorResult.EmptyResult()
            }
    } catch (e: Exception) {
        return ErrorResult.Error(e)
    }
}