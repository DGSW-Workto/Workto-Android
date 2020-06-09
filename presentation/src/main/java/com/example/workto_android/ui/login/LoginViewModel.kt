package com.example.workto_android.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.TokenManager
import com.example.domain.login.LoginUseCase
import com.example.domain.result.Event
import com.example.domain.result.Result
import com.example.domain.token.SaveTokenUseCase
import com.example.model.LoginParameter
import com.example.workto_android.ui.BaseViewModel

class LoginViewModel(private val loginUseCase: LoginUseCase, private val saveTokenUseCase: SaveTokenUseCase) : BaseViewModel() {

    val id = ObservableField("")
    val password = ObservableField("")

    private val _buttonEnabled = MutableLiveData<Boolean>(false)
    val buttonEnabled : LiveData<Boolean>
        get() = _buttonEnabled

    private val loginResult = loginUseCase.observe()

    private val _completeLogin = MediatorLiveData<Event<Unit>>()
    val completeLogin : LiveData<Event<Unit>>
        get() = _completeLogin

    init {
        loginResult.onSuccess(_completeLogin) {
            when (saveTokenUseCase(it.data.token)) {
                is Result.Error -> {
                    _error.value = Event("알 수 없는 에러 발생")
                }
            }
            TokenManager.token = it.data.token
            _completeLogin.value = Event(Unit)
        }

        loginResult.onError(_error) {
            _error.value = Event(it)
        }
    }

    fun onEditTextChanged() {
        _buttonEnabled.value = id.get()!!.isNotBlank() && password.get()!!.isNotBlank()
    }

    fun login() {
        this(loginUseCase(LoginParameter(id.get()!!, password.get()!!)))
    }
}