package com.example.sopt_homework.api

import com.example.sopt_homework.RequestData.RequestSignUpData
import com.example.sopt_homework.ResponseData.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpService{
    @Headers("Content-Type:application/json")
    @POST("/users/signup")
    fun postSignUp(
        @Body body : RequestSignUpData
    ) : Call<ResponseSignUpData>
}