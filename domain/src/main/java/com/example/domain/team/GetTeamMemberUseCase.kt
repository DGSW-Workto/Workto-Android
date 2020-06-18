package com.example.domain.team

import com.example.domain.SingleUseCase
import com.example.domain.result.Result
import com.example.model.TeamMemberData
import io.reactivex.Single

class GetTeamMemberUseCase(private val teamRepository: TeamRepository) : SingleUseCase<Pair<Int, Int>, TeamMemberData>() {
    override fun execute(parameter: Pair<Int, Int>): Single<Result<TeamMemberData>> {
        return teamRepository.getTeamMember(parameter)
    }
}