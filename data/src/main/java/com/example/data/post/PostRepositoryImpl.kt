package com.example.data.post

import com.example.domain.post.PostRepository
import com.example.domain.result.Result
import com.example.model.PostData
import com.example.model.PostDetail
import io.reactivex.Single

class PostRepositoryImpl(
    private val postRemoteDataSource: PostRemoteDataSource,
    private val postListMapper: PostListMapper,
    private val postDetailMapper: PostDetailMapper
) : PostRepository {
    override fun getPostList(page: Int): Single<Result<PostData>> {
        return postRemoteDataSource.getPostList(page).map(postListMapper::map)
    }

    override fun getPostDetail(id: Int): Single<Result<PostDetail>> {
        return postRemoteDataSource.getPostDetail(id).map(postDetailMapper::map)
    }
}