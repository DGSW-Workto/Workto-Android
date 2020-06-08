package com.example.data.login

import com.example.model.JoinParameter
import com.example.model.LoginData
import com.example.model.LoginParameter
import com.example.model.Response
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("/join/")
    fun join(@Body joinParameter: JoinParameter): Single<retrofit2.Response<Response<Unit>>>

    @POST("/login/")
    fun login(@Body loginParameter: LoginParameter): Single<retrofit2.Response<Response<LoginData>>>
}