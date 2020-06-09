package com.example.data.token

import com.example.model.Response
import com.example.model.TokenData
import io.reactivex.Single

class TokenRemoteDataSource(private val tokenApi: TokenApi) {
    fun checkToken(): Single<retrofit2.Response<Response<TokenData>>> {
        return tokenApi.checkToken()
    }
}