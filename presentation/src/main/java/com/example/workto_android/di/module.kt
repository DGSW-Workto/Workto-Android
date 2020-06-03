package com.example.workto_android.di

import com.example.workto_android.ui.start.StartViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { StartViewModel() }
}

val moduleList = listOf(viewModelModule)