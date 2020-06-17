package com.example.data.post

import com.example.data.DateTimeConverter
import com.example.data.NetworkDataMapper
import com.example.domain.result.Result
import com.example.model.PostDetail

class PostDetailMapper(private val dateTimeConverter: DateTimeConverter) : NetworkDataMapper<PostDetail>() {
    override fun mapTo(data: PostDetail): Result<PostDetail> {
        return Result.Success(data.apply {
            post.create_date = dateTimeConverter.jsonTimeToTime(post.create_date)
        })
    }
}