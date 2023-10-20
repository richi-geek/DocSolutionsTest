package com.example.docsolutions.network

import com.example.docsolutions.model.LoginResponse
import com.example.docsolutions.model.NewUserPost
import com.example.docsolutions.model.NewUserResponse
import com.example.docsolutions.model.PostGetUsers
import com.example.docsolutions.model.PostLogin
import com.example.docsolutions.model.UsersResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("OnBoardingPre/WebApi/api/authentication/authentication")
    fun postLogin(@Body body: PostLogin): Call<LoginResponse>

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("OnBoardingPre/WebApi/api/user/GetUsers")
    fun getUsers(@Body body: PostGetUsers, @Header("Authorization") authHeader: String): Call<UsersResponse>

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("OnBoardingPre/WebApi/api/user/RegisterUserRole")
    fun postNewUser(@Body body: NewUserPost, @Header("Authorization") authHeader: String): Call<NewUserResponse>
}