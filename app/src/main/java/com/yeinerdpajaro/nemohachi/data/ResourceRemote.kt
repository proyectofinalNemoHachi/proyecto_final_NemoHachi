package com.yeinerdpajaro.nemohachi.data

sealed class ResourceRemote<T> (
    var data: T? = null,
    var message: String? =null,
    var status: Status? = null,
    var errorCode: Int? = null

){
    class Success<T>(data: T): ResourceRemote<T>(data = data, status = Status.Success)

    class Loaging<T>(message: String = "") : ResourceRemote<T>(message = message,status = Status.Loading)

    class Error<T>(errorCode: Int? = null, message: String? = null) : ResourceRemote<T>(errorCode = errorCode, message = message,status = Status.Error)

}
enum class Status{
    Success,
    Error ,
    Loading
}