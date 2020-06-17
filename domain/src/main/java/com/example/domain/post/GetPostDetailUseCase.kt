package com.example.domain.post

import com.example.domain.SingleUseCase
import com.example.domain.result.Result
import com.example.model.PostDetail
import io.reactivex.Single

class GetPostDetailUseCase(private val postRepository: PostRepository) : SingleUseCase<Int, PostDetail>() {
    override fun execute(parameter: Int): Single<Result<PostDetail>> {
        return postRepository.getPostDetail(parameter)
    }
}