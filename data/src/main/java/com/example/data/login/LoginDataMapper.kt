package com.example.data.login

import com.example.data.NetworkDataMapper
import com.example.domain.result.Result
import com.example.model.LoginData

class LoginDataMapper : NetworkDataMapper<LoginData>() {
    override fun mapTo(data: LoginData): Result<LoginData> {
        return Result.Success(data)
    }
}