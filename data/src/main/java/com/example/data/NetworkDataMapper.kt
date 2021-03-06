package com.example.data

import com.example.domain.TokenException
import com.example.domain.result.Result
import com.example.model.Response

abstract class NetworkDataMapper<R> {
    fun map(data: retrofit2.Response<Response<R>>): Result<R> {
        return if (data.isSuccessful) {
            if (data.body()?.status == 200)
                mapTo(data.body()?.data!!)
            else
                Result.Error(data.body()?.message!!)
        } else {
            if (data.code() == 401)
                throw TokenException(data)
            else
                Result.Error("server")
        }
    }

    abstract fun mapTo(data: R): Result<R>

}