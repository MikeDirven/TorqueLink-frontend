package nl.torquelink.data.network.result

import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

interface DecoderConfig<T> {
    val statusCodeOverrides: MutableMap<HttpStatusCode, HttpResponse.() -> Result<T>>

    infix fun HttpStatusCode.override(handler: HttpResponse.() -> Result<T>){
        statusCodeOverrides[this] = handler
    }
}