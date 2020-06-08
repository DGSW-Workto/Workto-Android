package com.example.data

import com.example.domain.result.Result

class CommonDataMapper<T> : NetworkDataMapper<T>() {
    override fun mapTo(data: T): Result<T> {
        return Result.Success(data)
    }
}