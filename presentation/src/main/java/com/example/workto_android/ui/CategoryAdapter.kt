package com.example.workto_android.ui

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.model.Category
import com.example.workto_android.R
import com.example.workto_android.databinding.CategoryItemBinding
import com.example.workto_android.util.CategorySelector
import com.google.android.flexbox.FlexboxLayout

class CategoryAdapter(
    private val categoryItem: ArrayList<Category>,
    private val categorySelector: CategorySelector
) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    var selectedCategory = arrayListOf<Category>()

    class CategoryHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val holderLayout = binding.holderLayout
        val textView = binding.textViewCategoryName
        fun bind(item: Category) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryHolder(
        CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).apply {
            selector = this@CategoryAdapter.categorySelector
        }
    )

    override fun getItemCount() = categoryItem.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {

        if (selectedCategory.contains(categoryItem[position])) {
            setBackground(
                R.drawable.category_select_background,
                "#ffffff",
                holder.textView,
                holder.holderLayout
            )
        } else {
            setBackground(
                R.drawable.category_unselect_background,
                "#000000",
                holder.textView,
                holder.holderLayout
            )
        }

        holder.bind(categoryItem[position])
    }

    private fun setBackground(
        drawable: Int,
        color: String,
        textView: TextView,
        holder: ConstraintLayout
    ) {
        textView.setTextColor(Color.parseColor(color))
        holder.setBackgroundResource(drawable)
    }
}