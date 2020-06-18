package com.example.data.team

import com.example.data.NetworkDataMapper
import com.example.domain.result.Result
import com.example.model.TeamMemberData

class TeamMemberMapper : NetworkDataMapper<TeamMemberData>() {
    override fun mapTo(data: TeamMemberData): Result<TeamMemberData> {
        return Result.Success(data)
    }
}