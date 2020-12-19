package com.example.sopt_homework.api


import com.example.sopt_homework.ResponseData.ResponseKakaoData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoService{
    @Headers("Authorization:KakaoAK 310946a77e3b1f2d3f2ad957864a948f")
    @GET("/v2/search/web")
    fun getWebSearch(
        @Query("query") web : String
    ):Call<ResponseKakaoData>
}