package com.example.sopt_homework

import android.provider.ContactsContract
import com.google.gson.annotations.SerializedName
import java.util.*

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