package com.example.data.post

import com.example.data.DateTimeConverter
import com.example.data.NetworkDataMapper
import com.example.domain.result.Result
import com.example.model.PostData

class PostListMapper(private val dateTimeConverter: DateTimeConverter) : NetworkDataMapper<PostData>() {
    override fun mapTo(data: PostData): Result<PostData> {
        return Result.Success(data.apply {
            posts.map {
                it.create_date = dateTimeConverter.jsonTimeToTime(it.create_date)
            }
        })
    }
}