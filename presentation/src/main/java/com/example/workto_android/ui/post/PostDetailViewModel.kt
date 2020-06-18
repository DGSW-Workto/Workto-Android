package com.example.workto_android.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.domain.post.GetPostDetailUseCase
import com.example.domain.result.Event
import com.example.model.Post
import com.example.model.PostData
import com.example.model.Team
import com.example.workto_android.ui.BaseViewModel

class PostDetailViewModel(private val getPostDetailUseCase: GetPostDetailUseCase) :
    BaseViewModel() {

    private var isHandled = false

    private val _postDetail = MediatorLiveData<PostData>()
    val postDetail: LiveData<PostData>
        get() = _postDetail

    private val _navigateToTeam = MutableLiveData<Event<Int>>()
    val navigateToTeam: LiveData<Event<Int>>
        get() = _navigateToTeam

    private val _groupId = MutableLiveData<Int>()

    private val getPostDetailResult = getPostDetailUseCase.observe()

    init {
        _postDetail.addSource(_groupId) {
            if (isHandled) return@addSource
            this(getPostDetailUseCase(it))
            isHandled = true
        }

        getPostDetailResult.onSuccess(_postDetail) {
            _postDetail.value = it.data.post.apply {
                this.info.run {
                    title = "$title (${count}명)"
                    create_date = "등록일 : $create_date"
                }
            }
        }

        getPostDetailResult.onError(_error) {
            _error.value = Event(it)
        }
    }

    fun navigateToTeam(id: Int) {
        _navigateToTeam.value = Event(id)
    }

    fun setGroupId(id: Int) {
        _groupId.value = id
    }
}