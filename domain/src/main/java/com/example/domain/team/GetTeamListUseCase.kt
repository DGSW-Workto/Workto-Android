package com.example.domain.team

import com.example.domain.SingleUseCase
import com.example.domain.result.Result
import com.example.model.TeamData
import io.reactivex.Single

class GetTeamListUseCase(private val teamRepository: TeamRepository) :
    SingleUseCase<Pair<Int, Int>, TeamData>() {
    override fun execute(parameter: Pair<Int, Int>): Single<Result<TeamData>> {
        return parameter.run {
            teamRepository.getTeamList(first.toString(), second.toString())
        }
    }
}