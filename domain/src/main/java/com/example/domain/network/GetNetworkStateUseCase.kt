package com.example.domain.network

import com.example.domain.UseCase
import java.lang.Exception
import com.example.domain.result.Result

class GetNetworkStateUseCase(private val networkManager: NetworkManager) :
    UseCase<Unit, Boolean>() {
    override fun execute(parameter: Unit): Result<Boolean> {
        return try {
            val state = networkManager.hasNetworkConnection()
            return if (state)
                Result.Success(state)
            else
                Result.Error("error")
        } catch (e: Exception) {
            Result.Error("error")
        }
    }
}