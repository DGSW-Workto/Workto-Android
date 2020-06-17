package com.example.workto_android.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.domain.post.GetPostDetailUseCase
import com.example.domain.result.Event
import com.example.model.Post
import com.example.model.PostDetail
import com.example.model.Team
import com.example.workto_android.ui.BaseViewModel

class PostDetailViewModel(private val getPostDetailUseCase: GetPostDetailUseCase) : BaseViewModel() {

    var isHandled = false

    private val _postDetail = MediatorLiveData<PostDetail>()
    val postDetail : LiveData<PostDetail>
        get() = _postDetail

    private val _groupId = MutableLiveData<Int>()

    private val getPostDetailResult = getPostDetailUseCase.observe()

    init {
        _postDetail.addSource(_groupId) {
            if (isHandled) return@addSource
            this(getPostDetailUseCase(it))
            isHandled = true
        }

        getPostDetailResult.onSuccess(_postDetail) {
            _postDetail.value = it.data!!
        }

        getPostDetailResult.onError(_error) {
            _error.value = Event(it)
        }
    }

    fun setGroupId(id: Int) {
        _groupId.value = id
    }
}