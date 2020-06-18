package com.example.domain.team

import com.example.domain.result.Result
import com.example.model.TeamData
import com.example.model.TeamMemberData
import io.reactivex.Single

interface TeamRepository {
    fun getTeamList(isMine: String, page: String) : Single<Result<TeamData>>
    fun getTeamMember(parameter: Pair<Int, Int>) : Single<Result<TeamMemberData>>
}