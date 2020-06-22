package com.example.workto_android.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.model.PostData
import com.example.workto_android.databinding.PostItemBinding
import com.example.workto_android.util.PostHelper

class PostAdapter(private val postHelper: PostHelper) :
RecyclerView.Adapter<PostAdapter.PostHolder>() {

    class PostHolder(private val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PostData) {
            binding.item = item
        }
    }

    var nextPage = 0
    var postList = ArrayList<PostData>()
    var canLoadMore = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostHolder(
        PostItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            postHelper = this@PostAdapter.postHelper
        }
    )

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        if (position >= postList.size - 1 && canLoadMore) {
            canLoadMore = false
            postHelper.executeSearch(nextPage)
        }

        holder.bind(postList[position])
    }
}