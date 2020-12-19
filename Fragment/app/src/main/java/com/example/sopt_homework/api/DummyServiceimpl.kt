package com.example.sopt_homework.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DummyServiceimpl {
    private const val BASE_URL = "http://reqres.in"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : DummyService = retrofit.create(DummyService::class.java)

}