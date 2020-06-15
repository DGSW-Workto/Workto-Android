package com.example.domain.post

import com.example.domain.SingleUseCase
import com.example.domain.result.Result
import com.example.model.PostData
import io.reactivex.Single

class GetPostListUseCase(private val postRepository: PostRepository) : SingleUseCase<Int, PostData>() {
    override fun execute(parameter: Int): Single<Result<PostData>> {
        return postRepository.getPostList(parameter)
    }
}