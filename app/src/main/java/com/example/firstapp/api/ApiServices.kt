package com.example.firstapp.api

import com.example.firstapp.response.UserDetails
import com.example.firstapp.response.UserList.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("api/character")
    suspend fun getUserList(@Query("page")page:Int
    ):UserResponse

    @GET("api/character/{id}")
    suspend fun getUserDetails(@Path("id")id:Int
    ):UserDetails


}