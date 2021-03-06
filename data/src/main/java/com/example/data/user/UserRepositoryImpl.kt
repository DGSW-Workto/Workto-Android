package com.example.data.user

import com.example.domain.result.Result
import com.example.domain.user.UserRepository
import com.example.model.UserData
import io.reactivex.Single

class UserRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userDataMapper: UserDataMapper
) : UserRepository {
    override fun getUserData(): Single<Result<UserData>> {
        return userRemoteDataSource.getUserData().map(userDataMapper::map)
    }
}