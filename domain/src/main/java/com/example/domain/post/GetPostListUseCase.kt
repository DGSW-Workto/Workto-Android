package com.example.domain.post

import com.example.domain.SingleUseCase
import com.example.domain.result.Result
import com.example.model.PostListData
import io.reactivex.Single

class GetPostListUseCase(private val postRepository: PostRepository) : SingleUseCase<Int, PostListData>() {
    override fun execute(parameter: Int): Single<Result<PostListData>> {
        return postRepository.getPostList(parameter)
    }
}