package com.example.data.post

import com.example.domain.post.PostRepository
import com.example.domain.result.Result
import com.example.model.PostData
import io.reactivex.Single

class PostRepositoryImpl(
    private val postRemoteDataSource: PostRemoteDataSource,
    private val postListMapper: PostListMapper
) : PostRepository {
    override fun getPostList(page: Int): Single<Result<PostData>> {
        return postRemoteDataSource.getPostList(page).map(postListMapper::map)
    }
}