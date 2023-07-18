package com.example.firstapp.response.UserList


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("info")
    val info: Info? = Info(),
    @SerializedName("results")
    val results: List<Result>? = listOf()
)