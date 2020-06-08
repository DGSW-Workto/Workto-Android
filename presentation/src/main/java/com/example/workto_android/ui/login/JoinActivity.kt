package com.example.workto_android.ui.login

import android.os.Bundle
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.model.Category
import com.example.workto_android.R
import com.example.workto_android.databinding.ActivityJoinBinding
import com.example.workto_android.ui.BaseActivity
import com.example.workto_android.ui.CategoryAdapter
import com.google.android.flexbox.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class JoinActivity : BaseActivity<JoinViewModel>() {
    override val viewModel: JoinViewModel by viewModel()
    private lateinit var binding: ActivityJoinBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityJoinBinding>(this, R.layout.activity_join)
            .apply {
                lifecycleOwner = this@JoinActivity
                viewModel = this@JoinActivity.viewModel
            }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.recyclerviewCategory.apply {
            layoutManager = FlexboxLayoutManager(this@JoinActivity).apply {
                flexWrap = FlexWrap.WRAP
                justifyContent = JustifyContent.FLEX_START
            }
            setHasFixedSize(true)
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
            adapter = CategoryAdapter(viewModel.categoryList, viewModel)
        }

        initObserver()

    }

    private fun initObserver() {
        viewModel.showCategory.observe(this, Observer {
            binding.motionLayout.transitionToEnd()
        })
    }

    private fun isLastPage(): Boolean {
        return if (viewModel.isLastPage) {
            viewModel.setFirst()
            binding.motionLayout.transitionToStart()
            true
        } else
            false

    }

    override fun onBackPressed() {
        if (!isLastPage())
            super.onBackPressed()
    }

}