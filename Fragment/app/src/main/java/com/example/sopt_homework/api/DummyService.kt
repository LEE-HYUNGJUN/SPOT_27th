package com.example.sopt_homework.api

import com.example.sopt_homework.RequestData.RequestLoginData
import com.example.sopt_homework.ResponseData.ResponseDummyData
import com.example.sopt_homework.ResponseData.ResponseLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface DummyService{
    @Headers("Content-Type:application/json")
    @POST("/api/users")
    fun postDummy(
        @Query("page") page : Int
    ) : Call<ResponseDummyData>
}