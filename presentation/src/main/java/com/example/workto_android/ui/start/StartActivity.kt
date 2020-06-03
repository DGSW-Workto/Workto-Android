package com.example.workto_android.ui.start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.workto_android.R
import com.example.workto_android.databinding.ActivityStartBinding
import com.example.workto_android.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartActivity : BaseActivity<StartViewModel>() {

    override val viewModel: StartViewModel by viewModel()

    lateinit var binding : ActivityStartBinding

    private val startFragment = StartFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityStartBinding>(this, R.layout.activity_start)

        showFragment(startFragment)
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(binding.fragmentHolder.id, fragment).commitAllowingStateLoss()
    }
}
