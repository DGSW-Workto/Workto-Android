package com.example.domain.login

import com.example.domain.SingleUseCase
import com.example.domain.result.Result
import com.example.model.LoginData
import com.example.model.LoginParameter
import io.reactivex.Single

class LoginUseCase(private val loginRepository: LoginRepository) : SingleUseCase<LoginParameter, LoginData>() {
    override fun execute(parameter: LoginParameter): Single<Result<LoginData>> {
        return loginRepository.login(parameter)
    }
}