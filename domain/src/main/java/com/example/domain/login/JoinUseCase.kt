package com.example.domain.login

import com.example.domain.SingleUseCase
import com.example.domain.result.Result
import com.example.model.JoinParameter
import io.reactivex.Single

class JoinUseCase(private val loginRepository: LoginRepository) : SingleUseCase<JoinParameter, String>() {
    override fun execute(parameter: JoinParameter): Single<Result<String>> {
        return loginRepository.join(parameter)
    }
}