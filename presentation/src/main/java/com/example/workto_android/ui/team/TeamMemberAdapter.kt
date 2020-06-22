package com.example.workto_android.ui.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.model.TeamMember
import com.example.workto_android.databinding.TeamMemberItemBinding

class TeamMemberAdapter : RecyclerView.Adapter<TeamMemberAdapter.TeamMemberHolder>() {

    var teamMemberList = ArrayList<TeamMember>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamMemberHolder(
        TeamMemberItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = teamMemberList.size

    override fun onBindViewHolder(holder: TeamMemberHolder, position: Int) {
        holder.bind(teamMemberList[position])
    }

    class TeamMemberHolder(private val binding: TeamMemberItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TeamMember) {
            binding.item = item
        }
    }

}