package com.example.workto_android.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.workto_android.R
import com.example.workto_android.databinding.MainMenuItemBinding

class MainMenuAdapter(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<MainMenuAdapter.MainMenuHolder>() {

    var menuItem = getMenu()

    class MainMenuHolder(private val binding: MainMenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MainMenu) {

            binding.imageViewMenu.run {
                this.setImageDrawable(ContextCompat.getDrawable(context, item.drawable))
            }
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainMenuHolder(
        MainMenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).apply {
            viewModel = this@MainMenuAdapter.viewModel
        }
    )

    override fun getItemCount() = menuItem.size

    override fun onBindViewHolder(holder: MainMenuHolder, position: Int) {
        holder.bind(menuItem[position])
    }

    private fun getMenu(): ArrayList<MainMenu> {
        return listOf(
            MainMenu(R.drawable.ic_team, "팀 생성"),
            MainMenu(R.drawable.ic_edit, "포스트 등록"),
            MainMenu(R.drawable.ic_my_page, "내 페이지"),
            MainMenu(R.drawable.ic_post, "내 포스트")
        ).toCollection(ArrayList())
    }
}