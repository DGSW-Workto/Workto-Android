package com.example.workto_android.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Team
import com.example.workto_android.databinding.TeamItemBinding

class TeamAdapter(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<TeamAdapter.TeamHolder>() {

    class TeamHolder(private val binding: TeamItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Team) {
            binding.item = item
        }
    }

    var nextPage = 0
    var postList = ArrayList<Team>()
    var canLoadMore = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamHolder(
        TeamItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            viewModel = this@TeamAdapter.viewModel
        }
    )

    override fun getItemCount() = postList.size

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {

        if (position >= postList.size - 1 && canLoadMore) {
            canLoadMore = false
            viewModel.executeSearch(nextPage)
        }

        holder.bind(postList[position])
    }
}