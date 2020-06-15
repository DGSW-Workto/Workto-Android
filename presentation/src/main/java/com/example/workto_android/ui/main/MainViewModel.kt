package com.example.workto_android.ui.main

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.result.Event
import com.example.domain.user.GetUserDataUseCase
import com.example.model.Category
import com.example.model.UserData
import com.example.workto_android.R
import com.example.workto_android.ui.BaseViewModel
import com.example.workto_android.util.CategorySelector
import com.example.workto_android.util.UserDataManager

class MainViewModel(private val getUserDataUseCase: GetUserDataUseCase) : BaseViewModel(),
    CategorySelector {

    val searchWord = ObservableField("")

    private val _userData = MediatorLiveData<UserData>()
    val userData: LiveData<UserData>
        get() = _userData

    private val _searchByTeam = MutableLiveData(true)
    val searchByTeam: LiveData<Boolean>
        get() = _searchByTeam

    private val _searchByPost = MutableLiveData(false)
    val searchByPost: LiveData<Boolean>
        get() = _searchByPost


    private val _navigateToCreateTeam = MutableLiveData<Event<Unit>>()
    val navigateToCreateTeam: LiveData<Event<Unit>>
        get() = _navigateToCreateTeam

    private val _navigateToWritePost = MutableLiveData<Event<Unit>>()
    val navigateToWritePost: LiveData<Event<Unit>>
        get() = _navigateToWritePost

    private val _navigateToMyPage = MutableLiveData<Event<Unit>>()
    val navigateToMyPage: LiveData<Event<Unit>>
        get() = _navigateToMyPage

    private val _navigateToMyPost = MutableLiveData<Event<Unit>>()
    val navigateToMyPost: LiveData<Event<Unit>>
        get() = _navigateToMyPost

    private val _bottomSheetState = MutableLiveData<Boolean>(false)
    val bottomSheetState : LiveData<Boolean>
        get() = _bottomSheetState

    private val _selectedCategory = MutableLiveData<Pair<Int, ArrayList<Category>>>()
    val selectedCategory: LiveData<Pair<Int, ArrayList<Category>>>
        get() = _selectedCategory

    private val allSelectedCategory = arrayListOf<Category>()
    val categoryList: ArrayList<Category> = Category.values().toCollection(ArrayList())

    init {

        this(getUserDataUseCase(Unit))

        getUserDataUseCase.observe().onSuccess(_userData) {
            UserDataManager.userData = it.data
            _userData.value = it.data!!
        }

        getUserDataUseCase.observe().onError(_error) {

        }
    }

    fun openSearchHolder() {
        _bottomSheetState.value = true
    }

    fun toggleSearchMode() {
        _searchByPost.value = _searchByTeam.value
        _searchByTeam.value = !(_searchByTeam.value as Boolean)
    }

    fun navigate(mainMenu: MainMenu) {
        when (mainMenu.name) {
            "팀 생성" -> navigateTo(_navigateToCreateTeam)
            "포스트 등록" -> navigateTo(_navigateToWritePost)
            "내 페이지" -> navigateTo(_navigateToMyPage)
            "내 포스트" -> navigateTo(_navigateToMyPost)
        }
    }

    fun executeSearch() {
        _bottomSheetState.value = false
    }

    private fun navigateTo(navigate: MutableLiveData<Event<Unit>>) {
        navigate.value = Event(Unit)
    }

    fun clearSearchWord() {
        searchWord.set("")
    }

    override fun selectCategory(category: Category) {
        if (allSelectedCategory.contains(category))
            allSelectedCategory.remove(category)
        else
            allSelectedCategory.add(category)

        val position = categoryList.indexOf(category)
        _selectedCategory.value = Pair(position, allSelectedCategory)
    }

}