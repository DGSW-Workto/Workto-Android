package com.example.workto_android.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.workto_android.ui.BaseViewModel

class MainViewModel : BaseViewModel() {

    private val _test = MutableLiveData<String>()
    val test : LiveData<String>
        get() = _test

    fun test() {
        _test.value = "TEST"
    }
}