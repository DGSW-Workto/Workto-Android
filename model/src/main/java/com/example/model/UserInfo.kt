package com.example.model

data class UserInfo(
    val id: String,
    val nickname: String,
    val email: String,
    val skills: ArrayList<String>
)