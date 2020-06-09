package com.example.domain.token

import com.example.domain.SingleUseCase
import com.example.domain.UseCase
import com.example.domain.result.Result
import io.reactivex.Single

class GetTokenUseCase(private val tokenRepository: TokenRepository) : UseCase<Unit, String>() {
    override fun execute(parameter: Unit): Result<String> {
        return tokenRepository.getToken()
    }

}