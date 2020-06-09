package com.example.domain.token

import com.example.domain.result.Result
import com.example.model.TokenData
import io.reactivex.Single

interface TokenRepository {
    fun getToken(): Result<String>
    fun saveToken(token: String): Result<Unit>
    fun deleteToken() : Result<Unit>
    fun checkToken(): Single<Result<TokenData>>
}