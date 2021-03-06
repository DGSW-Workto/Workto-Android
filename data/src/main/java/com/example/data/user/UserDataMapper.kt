package com.example.data.user

import com.example.data.NetworkDataMapper
import com.example.domain.result.Result
import com.example.model.UserData

class UserDataMapper : NetworkDataMapper<UserData>() {
    override fun mapTo(data: UserData): Result<UserData> {
        return Result.Success(data)
    }
}