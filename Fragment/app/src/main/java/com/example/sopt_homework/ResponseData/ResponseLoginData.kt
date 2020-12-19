package com.example.sopt_homework.ResponseData

import com.google.gson.annotations.SerializedName

data class ResponseLoginData(
    val data : Data,
    @SerializedName("message")
    val responseMessage : String,
    val status : Int,
    val success : Boolean
){
    data class Data(
        val email : String,
        val password : String,
        val userName : String
    )
}