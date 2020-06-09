package com.example.workto_android.ui.main

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.workto_android.R
import com.example.workto_android.databinding.ActivityMainBinding
import com.example.workto_android.ui.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding

    private val mainMenuFragment by inject<MainMenuFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }

        binding.bottomAppBar.setNavigationOnClickListener {
            mainMenuFragment.show(supportFragmentManager, mainMenuFragment.tag)
        }

        initObserver()
    }

    private fun initObserver() {

    }


}
