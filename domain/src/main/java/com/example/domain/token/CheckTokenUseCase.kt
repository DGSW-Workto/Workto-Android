package com.example.domain.token

import com.example.domain.SingleUseCase
import com.example.domain.result.Result
import com.example.model.TokenData
import io.reactivex.Single

class CheckTokenUseCase(private val tokenRepository: TokenRepository) : SingleUseCase<Unit, TokenData>() {
    override fun execute(parameter: Unit): Single<Result<TokenData>> {
        return tokenRepository.checkToken()
    }
}