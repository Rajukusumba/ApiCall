package com.example.firstapp.api

sealed class ApiState<T>(
    val data: T? = null,
    val errorMessage: String = ""
) {
    class Success<T>(data: T?) : ApiState<T>(data)
    class Failure<T>(errorMessage: String?) : ApiState<T>(
        errorMessage = errorMessage ?: "no data"
    )

    class Loading<T> : ApiState<T>()
//    class Initial<T> : ApiState<T>()
}

//sealed class ApiState<T>(
//    val data: T? = null,
//    val errorMessage: String = ""
//) {
//    class Success<T>(data: T?) : ApiState<T>(data)
//    class Failure<T>(errorMessage: String?) : ApiState<T>(
//        errorMessage = errorMessage ?: "no data"
//    )
//
//    class Loading<T> : ApiState<T>()
////    class Initial<T> : ApiState<T>()
//}