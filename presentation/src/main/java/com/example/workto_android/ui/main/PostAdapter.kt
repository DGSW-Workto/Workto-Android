package com.example.workto_android.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Post
import com.example.workto_android.databinding.PostItemBinding

class PostAdapter(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<PostAdapter.PostHolder>() {

    class PostHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Post) {
            binding.item = item
        }
    }

    var nextPage = 0
    var postList = ArrayList<Post>()
    var canLoadMore = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostHolder(
        PostItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            viewModel = this@PostAdapter.viewModel
        }
    )

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        if (position >= postList.size - 1 && canLoadMore) {
            canLoadMore = false
            viewModel.executeSearch(nextPage)
        }

        holder.bind(postList[position])
    }
}