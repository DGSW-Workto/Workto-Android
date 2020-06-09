package com.example.data.token

import com.example.data.SharedPreferenceStorage
import com.example.domain.result.Result
import com.example.domain.token.TokenRepository
import com.example.model.TokenData
import io.reactivex.Single
import java.lang.Exception

class TokenRepositoryImpl(
    private val sharedPreferenceStorage: SharedPreferenceStorage,
    private val tokenDataMapper: TokenDataMapper,
    private val tokenRemoteDataSource: TokenRemoteDataSource
) :
    TokenRepository {
    override fun getToken(): Result<String> {
        val token = sharedPreferenceStorage.getString("token")
        return if (token.isNotBlank()) Result.Success(token) else Result.Error("no token")
    }

    override fun saveToken(token: String): Result<Unit> {
        return try {
            sharedPreferenceStorage.setString("token", token)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error("error")
        }
    }

    override fun deleteToken(): Result<Unit> {
        return try {
            sharedPreferenceStorage.removeKey("token")
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error("error")
        }
    }

    override fun checkToken(): Single<Result<TokenData>> {
        return tokenRemoteDataSource.checkToken().map(tokenDataMapper::map)
    }
}