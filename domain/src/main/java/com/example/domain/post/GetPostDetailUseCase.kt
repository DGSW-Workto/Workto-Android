package com.example.domain.post

import com.example.domain.SingleUseCase
import com.example.domain.result.Result
import com.example.model.PostData
import com.example.model.PostDetailData
import io.reactivex.Single

class GetPostDetailUseCase(private val postRepository: PostRepository) : SingleUseCase<Int, PostDetailData>() {
    override fun execute(parameter: Int): Single<Result<PostDetailData>> {
        return postRepository.getPostDetail(parameter)
    }
}