package com.example.domain.post

import com.example.domain.result.Result
import com.example.model.PostData
import com.example.model.PostListData
import com.example.model.PostDetailData
import io.reactivex.Single

interface PostRepository {
    fun getPostList(page: Int): Single<Result<PostListData>>
    fun getPostDetail(id: Int) : Single<Result<PostDetailData>>
}