package com.example.model

data class TeamMember(
    val member_id: String,
    val email: String,
    val nickname: String,
    val skills: ArrayList<String>
)