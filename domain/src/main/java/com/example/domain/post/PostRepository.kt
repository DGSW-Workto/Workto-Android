package com.example.domain.post

import com.example.domain.result.Result
import com.example.model.PostData
import com.example.model.PostDetail
import io.reactivex.Single

interface PostRepository {
    fun getPostList(page: Int): Single<Result<PostData>>
    fun getPostDetail(id: Int) : Single<Result<PostDetail>>
}