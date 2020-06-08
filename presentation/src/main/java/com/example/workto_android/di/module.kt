package com.example.workto_android.di

import com.example.workto_android.ui.login.JoinViewModel
import com.example.workto_android.ui.login.LoginViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { LoginViewModel() }
    factory { JoinViewModel() }
}

val moduleList = listOf(viewModelModule)