package com.example.data.post

import com.example.model.*
import io.reactivex.Single

class PostRemoteDataSource(private val api: PostApi) {

    fun getPostList(page: Int): Single<retrofit2.Response<Response<PostListData>>> {
        return api.getPostList(page.toString())
    }

    fun getPostDetail(id: Int): Single<retrofit2.Response<Response<PostDetailData>>> {
        return api.getPostDetail(id.toString())
    }
}