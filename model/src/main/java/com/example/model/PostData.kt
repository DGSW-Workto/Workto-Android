package com.example.model

data class PostData(
    val posts: ArrayList<Post>,
    val images: ArrayList<PostImage?>,
    val next_page: Int,
    var is_last: Boolean
)