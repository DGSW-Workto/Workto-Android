package com.example.workto_android.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.domain.result.EventObserver
import com.example.workto_android.R
import com.example.workto_android.databinding.ActivityLoginBinding
import com.example.workto_android.ui.BaseActivity
import com.example.workto_android.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModel()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
                .apply {
                    lifecycleOwner = this@LoginActivity
                    viewModel = this@LoginActivity.viewModel
                }

        binding.buttonJoin.setOnClickListener {
            startActivity(Intent(this, JoinActivity::class.java))
        }

        initObserver()
    }

    private fun initObserver() {
        viewModel.error.observe(this, EventObserver {
            makeToast(it, false)
        })

        viewModel.completeLogin.observe(this, EventObserver{
            makeToast("로그인 성공", false)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })
    }


}
