package com.example.data.team

import com.example.data.NetworkDataMapper
import com.example.domain.result.Result
import com.example.model.TeamData

class TeamListMapper : NetworkDataMapper<TeamData>() {
    override fun mapTo(data: TeamData): Result<TeamData> {
        return Result.Success(data)
    }
}