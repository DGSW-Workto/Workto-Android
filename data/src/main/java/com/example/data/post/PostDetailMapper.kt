package com.example.data.post

import com.example.data.DateTimeConverter
import com.example.data.NetworkDataMapper
import com.example.domain.result.Result
import com.example.model.PostData
import com.example.model.PostDetailData

class PostDetailMapper(private val dateTimeConverter: DateTimeConverter) : NetworkDataMapper<PostDetailData>() {
    override fun mapTo(data: PostDetailData): Result<PostDetailData> {
        return Result.Success(data.apply {
            post.info.create_date = dateTimeConverter.jsonTimeToTime(post.info.create_date)
        })
    }
}