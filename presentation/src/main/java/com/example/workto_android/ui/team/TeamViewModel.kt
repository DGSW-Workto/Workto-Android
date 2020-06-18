package com.example.workto_android.ui.team

import androidx.lifecycle.MediatorLiveData
import com.example.domain.team.GetTeamMemberUseCase
import com.example.model.TeamData
import com.example.workto_android.ui.BaseViewModel

class TeamViewModel(private val getTeamMemberUseCase: GetTeamMemberUseCase) : BaseViewModel() {

    private var isHandled = false



    init {

    }
}