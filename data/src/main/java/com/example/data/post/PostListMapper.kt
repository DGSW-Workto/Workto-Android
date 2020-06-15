package com.example.data.post

import com.example.data.NetworkDataMapper
import com.example.domain.result.Result
import com.example.model.PostData

class PostListMapper : NetworkDataMapper<PostData>() {
    override fun mapTo(data: PostData): Result<PostData> {
        return Result.Success(data)
    }
}