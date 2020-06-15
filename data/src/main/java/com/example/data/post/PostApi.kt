package com.example.data.post

import com.example.model.PostData
import com.example.model.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("/post/list")
    fun getPostList(@Query("page_num") page: String) : Single<retrofit2.Response<Response<PostData>>>
}