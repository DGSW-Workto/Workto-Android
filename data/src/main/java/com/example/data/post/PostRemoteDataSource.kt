package com.example.data.post

import com.example.model.PostData
import com.example.model.PostDetail
import com.example.model.Response
import io.reactivex.Single

class PostRemoteDataSource(private val api: PostApi) {

    fun getPostList(page: Int): Single<retrofit2.Response<Response<PostData>>> {
        return api.getPostList(page.toString())
    }

    fun getPostDetail(id: Int): Single<retrofit2.Response<Response<PostDetail>>> {
        return api.getPostDetail(id.toString())
    }
}