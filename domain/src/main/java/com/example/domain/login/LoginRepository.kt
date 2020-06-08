package com.example.domain.login

import com.example.domain.result.Result
import com.example.model.JoinParameter
import com.example.model.LoginData
import com.example.model.LoginParameter
import io.reactivex.Single

interface LoginRepository {
    fun join(joinParameter: JoinParameter) : Single<Result<String>>
    fun login(loginParameter: LoginParameter) : Single<Result<LoginData>>
}