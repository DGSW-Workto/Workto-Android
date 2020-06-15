package com.example.workto_android.ui.main

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.domain.result.EventObserver
import com.example.workto_android.R
import com.example.workto_android.databinding.ActivityMainBinding
import com.example.workto_android.ui.BaseActivity
import com.example.workto_android.ui.CategoryAdapter
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    private val mainMenuFragment by inject<MainMenuFragment>()
    private val teamListFragment by inject<PostListFragment>()

    private lateinit var bottomSheet: BottomSheetBehavior<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                viewModel = this@MainActivity.viewModel
                lifecycleOwner = this@MainActivity
            }

        showFragment(teamListFragment)

        bottomSheet = BottomSheetBehavior.from(binding.searchHolder)

        binding.bottomAppBar.setNavigationOnClickListener {
            mainMenuFragment.show(supportFragmentManager, mainMenuFragment.tag)
        }

        binding.recyclerviewCategory.apply {
            layoutManager = FlexboxLayoutManager(this@MainActivity).apply {
                flexWrap = FlexWrap.WRAP
                (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
                justifyContent = JustifyContent.FLEX_START
            }
            adapter = CategoryAdapter(viewModel.categoryList, viewModel)
        }

        initObserver()
    }

    private fun initObserver() {

        viewModel.bottomSheetState.observe(this, Observer {
            bottomSheet.state = if (it)
                BottomSheetBehavior.STATE_EXPANDED
            else
                BottomSheetBehavior.STATE_COLLAPSED

        })

        viewModel.navigateToCreateTeam.observe(this, EventObserver {

        })

        viewModel.navigateToMyPage.observe(this, EventObserver {

        })

        viewModel.navigateToMyPost.observe(this, EventObserver {

        })

        viewModel.navigateToWritePost.observe(this, EventObserver {

        })

    }

    override fun onBackPressed() {
        if (bottomSheet.state == BottomSheetBehavior.STATE_EXPANDED)
            bottomSheet.state = BottomSheetBehavior.STATE_COLLAPSED
        else
            super.onBackPressed()
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(binding.fragmentHolder.id, fragment).commitAllowingStateLoss()
    }

}

@BindingAdapter("text", "toggle")
fun toggleState(constraintLayout: ConstraintLayout, textView: TextView, isSelected: Boolean) {

    constraintLayout.run {
        if (isSelected) {
            background = ContextCompat.getDrawable(context, R.drawable.search_toggle_on_background)
            textView.setTextColor(Color.parseColor("#ffffff"))
        } else {
            background = ContextCompat.getDrawable(context, R.drawable.search_toggle_off_background)
            textView.setTextColor(Color.parseColor("#000000"))
        }
    }
}
