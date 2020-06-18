package com.example.data.post

import com.example.data.DateTimeConverter
import com.example.data.NetworkDataMapper
import com.example.domain.result.Result
import com.example.model.PostListData

class PostListMapper(private val dateTimeConverter: DateTimeConverter) : NetworkDataMapper<PostListData>() {
    override fun mapTo(listData: PostListData): Result<PostListData> {
        return Result.Success(listData.apply {
            post.map {
                it.info.create_date = dateTimeConverter.jsonTimeToTime(it.info.create_date)
            }
        })
    }
}