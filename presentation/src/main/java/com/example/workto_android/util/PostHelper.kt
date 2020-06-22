package com.example.workto_android.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.model.PostListData
import com.example.workto_android.ui.main.PostAdapter

interface PostHelper {
    fun selectPost(id: Int)
    fun executeSearch(page: Int)
}

@BindingAdapter("post_item")
fun setPostItem(recyclerView: RecyclerView, postListData: PostListData?) {
    val postAdapter: PostAdapter

    if (recyclerView.adapter == null)
        return
    else
        postAdapter = recyclerView.adapter as PostAdapter

    postListData?.let {
        postAdapter.canLoadMore = postListData.next_page != 0
        postAdapter.postList = postListData.post
        postAdapter.nextPage = postListData.next_page
        postAdapter.notifyDataSetChanged()
    }
}