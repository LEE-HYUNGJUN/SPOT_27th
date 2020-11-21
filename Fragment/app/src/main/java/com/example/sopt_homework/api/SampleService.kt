package com.example.sopt_homework.api

import com.example.sopt_homework.RequestLoginData
import com.example.sopt_homework.ResponseLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SampleService{
    @Headers("Content-Type:application/json")
    @POST("/users/signin")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>
}