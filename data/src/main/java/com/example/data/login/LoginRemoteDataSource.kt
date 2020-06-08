package com.example.data.login

import com.example.model.JoinParameter
import com.example.model.LoginData
import com.example.model.LoginParameter
import com.example.model.Response
import io.reactivex.Single

class LoginRemoteDataSource(private val api: LoginApi) {
    fun join(joinParameter: JoinParameter): Single<retrofit2.Response<Response<Unit>>> {
        return api.join(joinParameter)
    }

    fun login(loginParameter: LoginParameter): Single<retrofit2.Response<Response<LoginData>>> {
        return api.login(loginParameter)
    }
}