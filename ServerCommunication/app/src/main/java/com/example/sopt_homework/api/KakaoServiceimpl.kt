package com.example.sopt_homework.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KakaoServiceimpl {
    private const val BASE_URL = "https://dapi.kakao.com"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : KakaoService = retrofit.create(KakaoService::class.java)
}