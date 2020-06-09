package com.example.domain.token

import com.example.domain.UseCase
import com.example.domain.result.Result

class SaveTokenUseCase(private val tokenRepository: TokenRepository) : UseCase<String, Unit>() {
    override fun execute(parameter: String): Result<Unit> {
        return tokenRepository.saveToken(parameter)
    }

}