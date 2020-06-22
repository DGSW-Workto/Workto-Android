package com.example.workto_android.ui.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.post.GetPostListUseCase
import com.example.domain.result.Event
import com.example.domain.team.GetTeamMemberUseCase
import com.example.model.PostData
import com.example.model.PostListData
import com.example.model.TeamMemberData
import com.example.workto_android.ui.BaseViewModel
import com.example.workto_android.util.PostHelper

class TeamViewModel(
    private val getTeamMemberUseCase: GetTeamMemberUseCase,
    private val getPostListUseCase: GetPostListUseCase) : BaseViewModel(), PostHelper {

    private var isHandled = false

    private val groupId = MutableLiveData<Int>()

    private val getTeamMemberResult = getTeamMemberUseCase.observe()
    private val getPostListResult = getPostListUseCase.observe()

    private val _navigateToPostDetail = MutableLiveData<Event<Int>>()
    val navigateToPostDetail: LiveData<Event<Int>>
        get() = _navigateToPostDetail

    private val _teamMemberList = MediatorLiveData<TeamMemberData>()
    val teamMemberList: LiveData<TeamMemberData>
        get() = _teamMemberList

    private val allPostList = ArrayList<PostData>()

    private val _postList = MediatorLiveData<PostListData>()
    val postList: LiveData<PostListData>
        get() = _postList

    init {

        _teamMemberList.addSource(groupId) {
            if (isHandled) return@addSource
            this(getTeamMemberUseCase(Pair(0, it)))
            isHandled = true
        }

        getTeamMemberResult.onSuccess(_teamMemberList) {
            _teamMemberList.value = it.data!!
        }

        getTeamMemberResult.onError(_error) {
            _error.value = Event(it)
        }

        getPostListResult.onSuccess(_postList) {
            allPostList.addAll(it.data.post)
            it.data.post.clear()
            it.data.post.addAll(allPostList)
            _postList.value = it.data
        }

        getPostListResult.onError(_error) {
            _error.value = Event(it)
        }

    }

    fun setGroupId(id: Int) {
        groupId.value = id
    }

    override fun selectPost(id: Int) {
        _navigateToPostDetail.value = Event(id)
    }

    override fun executeSearch(page: Int) {
        this(getPostListUseCase(page))
    }
}