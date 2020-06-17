package com.example.data.team

import com.example.domain.result.Result
import com.example.domain.team.TeamRepository
import com.example.model.TeamData
import io.reactivex.Single

class TeamRepositoryImpl(private val teamRemoteDataSource: TeamRemoteDataSource, private val teamListMapper: TeamListMapper):TeamRepository {
    override fun getTeamList(isMine: String, page: String): Single<Result<TeamData>> {
        return teamRemoteDataSource.getTeamList(isMine, page).map(teamListMapper::map)
    }

}