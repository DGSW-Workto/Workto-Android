package com.example.data.token

import com.example.data.NetworkDataMapper
import com.example.domain.result.Result
import com.example.model.TokenData

class TokenDataMapper : NetworkDataMapper<TokenData>() {
    override fun mapTo(data: TokenData): Result<TokenData> {
        return Result.Success(data)
    }
}