package com.example.data.user

import com.example.model.Response
import com.example.model.UserData
import io.reactivex.Single
import retrofit2.http.GET

interface UserApi {

    @GET("/member/")
    fun  getUserData() : Single<retrofit2.Response<Response<UserData>>>
}