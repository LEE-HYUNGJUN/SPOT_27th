package com.example.sopt_homework.ResponseData

data class ResponseSignUpData(
    val `data`: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val email: String,
        val password: String,
        val userName: String
    )
}