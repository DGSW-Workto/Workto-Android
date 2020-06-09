package com.example.workto_android.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.result.Event
import com.example.domain.user.GetUserDataUseCase
import com.example.model.UserData
import com.example.workto_android.R
import com.example.workto_android.ui.BaseViewModel
import com.example.workto_android.util.UserDataManager

class MainViewModel(private val getUserDataUseCase: GetUserDataUseCase) : BaseViewModel() {

    private val _userData = MediatorLiveData<UserData>()
    val userData: LiveData<UserData>
        get() = _userData

    private val _navigateToCreateTeam = MutableLiveData<Event<Unit>>()
    val navigateToCreateTeam : LiveData<Event<Unit>>
        get() = _navigateToCreateTeam

    private val _navigateToWritePost = MutableLiveData<Event<Unit>>()
    val navigateToWritePost : LiveData<Event<Unit>>
        get() = _navigateToWritePost

    private val _navigateToMyPage = MutableLiveData<Event<Unit>>()
    val navigateToMyPage : LiveData<Event<Unit>>
        get() = _navigateToMyPage

    private val _navigateToMyPost = MutableLiveData<Event<Unit>>()
    val navigateToMyPost : LiveData<Event<Unit>>
        get() = _navigateToMyPost



    init {

        this(getUserDataUseCase(Unit))

        getUserDataUseCase.observe().onSuccess(_userData) {
            UserDataManager.userData = it.data
            _userData.value = it.data
        }

        getUserDataUseCase.observe().onError(_error) {

        }
    }

    fun navigate(mainMenu: MainMenu) {
        when (mainMenu.name) {
            "팀 생성" -> navigateTo(_navigateToCreateTeam)
            "포스트 등록" ->  navigateTo(_navigateToWritePost)
            "내 페이지" -> navigateTo(_navigateToMyPage)
            "내 포스트" -> navigateTo(_navigateToMyPost)
        }
    }

    private fun navigateTo(navigate: MutableLiveData<Event<Unit>>) {
        navigate.value = Event(Unit)
    }

}