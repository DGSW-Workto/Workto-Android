package com.example.workto_android.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.TokenManager
import com.example.domain.network.GetNetworkStateUseCase
import com.example.domain.result.Event
import com.example.domain.result.Result
import com.example.domain.token.CheckTokenUseCase
import com.example.domain.token.GetTokenUseCase
import com.example.workto_android.ui.BaseViewModel

class SplashViewModel(
    private val getTokenUseCase: GetTokenUseCase,
    private val checkTokenUseCase: CheckTokenUseCase,
    private val getNetworkStateUseCase: GetNetworkStateUseCase
) : BaseViewModel() {

    private val _tokenComplete = MediatorLiveData<Boolean>()
    val tokenComplete: LiveData<Boolean>
        get() = _tokenComplete

    private val checkTokenResult = checkTokenUseCase.observe()

    init {

        when (getNetworkStateUseCase(Unit)) {
            is Result.Success -> {
                getToken()
            }
            is Result.Error -> {
                _error.value = Event("네트워크 연결을 확인해주세요")
            }
        }

        checkTokenResult.onSuccess(_tokenComplete) {
            _tokenComplete.value = true
        }

        checkTokenResult.onError(_error) {
            when (it) {
                "network" -> _error.value = Event("네트워크 연결을 확인해주세요")
                else -> _tokenComplete.value = false
            }
        }
    }

    private fun getToken() {
        when (val it = getTokenUseCase(Unit)) {
            is Result.Success -> {
                TokenManager.token = it.data
                this(checkTokenUseCase(Unit))
            }
            is Result.Error -> {
                _tokenComplete.value = false
            }
        }
    }
}