package com.example.model

data class Post(
    val post_id: Int,
    val group_id: Int,
    var create_date: String,
    var title: String,
    val content: String,
    val count: String
)