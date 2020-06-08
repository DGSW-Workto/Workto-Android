package com.example.workto_android.ui.login

import android.os.Bundle
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.domain.result.EventObserver
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
                (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
                justifyContent = JustifyContent.FLEX_START
            }
            adapter = CategoryAdapter(viewModel.categoryList, viewModel)
        }

        initObserver()

    }

    private fun initObserver() {
        viewModel.showCategory.observe(this, Observer {
            binding.motionLayout.transitionToEnd()
        })

        viewModel.error.observe(this, EventObserver {
            makeToast(it, false)
        })

        viewModel.completeJoin.observe(this, EventObserver {
            makeToast("회원가입 성공", false)
        })

            binding.editTextPassword.doOnTextChanged { _, _, _, _ ->
            checkEquals()
            viewModel.onEditTextChanged()
        }

        binding.editTextPasswordCheck.doOnTextChanged { _, _, _, _ ->
            checkEquals()
            viewModel.onEditTextChanged()
        }
    }

    private fun isLastPage(): Boolean {
        return if (viewModel.isLastPage) {
            viewModel.setFirst()
            binding.motionLayout.transitionToStart()
            true
        } else
            false


    }

    private fun checkEquals() {
        binding.editTextPasswordCheck.error =
            if (binding.editTextPassword.text.toString() != binding.editTextPasswordCheck.text.toString()) {
                viewModel.isEquals = false
                "비밀번호가 일치하지 않습니다"
            } else {
                viewModel.isEquals = true
                null
            }
    }

    override fun onBackPressed() {
        if (!isLastPage())
            super.onBackPressed()
    }
}

@BindingAdapter("on_change")
fun onEditTextChanged(editText: EditText, lambda: () -> Unit) {
    editText.doOnTextChanged { text, start, count, after ->
        lambda()
    }
}

@BindingAdapter("selected_category_item")
fun selectCategoryItem(recyclerView: RecyclerView, items: Pair<Int, ArrayList<Category>>?) {
    val categoryAdapter: CategoryAdapter

    if (recyclerView.adapter == null)
        return
    else
        categoryAdapter = recyclerView.adapter as CategoryAdapter

    items?.let {
        categoryAdapter.selectedCategory = items.second
        categoryAdapter.notifyItemChanged(items.first)
    }
}
