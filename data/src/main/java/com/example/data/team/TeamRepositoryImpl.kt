package com.example.data.team

import com.example.domain.result.Result
import com.example.domain.team.TeamRepository
import com.example.model.TeamData
import com.example.model.TeamMemberData
import io.reactivex.Single

class TeamRepositoryImpl(private val teamRemoteDataSource: TeamRemoteDataSource, private val teamListMapper: TeamListMapper, private val teamMemberMapper: TeamMemberMapper):TeamRepository {
    override fun getTeamList(isMine: String, page: String): Single<Result<TeamData>> {
        return teamRemoteDataSource.getTeamList(isMine, page).map(teamListMapper::map)
    }

    override fun getTeamMember(parameter: Pair<Int, Int>): Single<Result<TeamMemberData>> {
        return teamRemoteDataSource.getTeamMember(parameter).map(teamMemberMapper::map)
    }

}