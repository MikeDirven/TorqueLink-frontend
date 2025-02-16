package nl.torquelink.data.network.result

sealed interface Result<T>

typealias EmptyResult = Result<Unit>

sealed interface ErrorResult<T> : Result<T> {
    class EmptyResult<T>: ErrorResult<T>
    data class Error<T>(val exception: Exception) : ErrorResult<T>
}

sealed interface SuccessResult<T> : Result<T> {
    data class WithBody<T>(val data: T) : SuccessResult<T>
    class EmptyResult<T>: SuccessResult<T>
}