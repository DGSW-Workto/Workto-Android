package com.example.data.team

import com.example.model.Response
import com.example.model.TeamData
import io.reactivex.Single

class TeamRemoteDataSource(private val api: TeamApi) {

    fun getTeamList(isMine: String, page: String): Single<retrofit2.Response<Response<TeamData>>> {
        return api.getTeamList(isMine, page)
    }
}