package com.example.model

data class UserData(
    val member_id: String,
    val nickname: String,
    val email: String,
    val skills: ArrayList<String>
)