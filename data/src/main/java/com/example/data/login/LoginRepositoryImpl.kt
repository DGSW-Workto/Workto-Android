package com.example.data.login

import com.example.data.CommonMessageMapper
import com.example.domain.login.LoginRepository
import com.example.domain.result.Result
import com.example.model.JoinParameter
import com.example.model.LoginData
import com.example.model.LoginParameter
import io.reactivex.Single

class LoginRepositoryImpl(private val loginRemoteDataSource: LoginRemoteDataSource, private val joinMapper: CommonMessageMapper, private val loginMapper: LoginDataMapper) :
    LoginRepository {
    override fun join(joinParameter: JoinParameter) : Single<Result<String>> {
        return loginRemoteDataSource.join(joinParameter).map(joinMapper::map)
    }

    override fun login(loginParameter: LoginParameter): Single<Result<LoginData>> {
        return loginRemoteDataSource.login(loginParameter).map(loginMapper::map)
    }
}