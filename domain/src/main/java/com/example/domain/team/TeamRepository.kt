package com.example.domain.team

import com.example.domain.result.Result
import com.example.model.TeamData
import io.reactivex.Single

interface TeamRepository {
    fun getTeamList(isMine: String, page: String) : Single<Result<TeamData>>
}