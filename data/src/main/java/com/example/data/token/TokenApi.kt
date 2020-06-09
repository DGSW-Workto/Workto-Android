package com.example.data.token

import com.example.model.Response
import com.example.model.TokenData
import io.reactivex.Single
import retrofit2.http.GET

interface TokenApi {

    @GET("/token/")
    fun checkToken() : Single<retrofit2.Response<Response<TokenData>>>
}