package com.example.data

import com.example.domain.result.Result

class CommonMessageMapper : NetworkMessageMapper() {
    override fun mapTo(data: String): Result<String> {
        return Result.Success(data)
    }
}