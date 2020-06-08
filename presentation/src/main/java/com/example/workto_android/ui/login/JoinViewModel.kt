package com.example.workto_android.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.model.Category
import com.example.workto_android.ui.BaseViewModel
import com.example.workto_android.util.CategorySelector

class JoinViewModel : BaseViewModel(), CategorySelector {

    var isLastPage = false

    private val _editTextEnabled = MutableLiveData(true)
    val editTextEnabled: LiveData<Boolean>
        get() = _editTextEnabled

    private val _buttonText = MutableLiveData<String>("다음")
    val buttonText: LiveData<String>
        get() = _buttonText

    private val _titleText = MutableLiveData("기본 정보를 입력해주세요")
    val titleText: LiveData<String>
        get() = _titleText

    private val _showCategory = MutableLiveData<Unit>()
    val showCategory: LiveData<Unit>
        get() = _showCategory

    private val selectedCategory = arrayListOf<Category>()
    val categoryList: ArrayList<Category> = Category.values().toCollection(ArrayList())


    fun onButtonClick() {
        if (isLastPage) {

        } else {
            setLast()
        }
    }

    fun setFirst() {
        _titleText.value = "기본 정보를 입력해주세요"
        _buttonText.value = "다음"
        _editTextEnabled.value = true
        isLastPage = false
    }

    private fun setLast() {
        _titleText.value = "관심 분야를 선택해주세요 (최대 3개)"
        _buttonText.value = "시작하기"
        _editTextEnabled.value = false
        _showCategory.value = Unit
        isLastPage = true
    }

    override fun selectCategory(category: Category) {
        if (selectedCategory.contains(category))
            selectedCategory.remove(category)
        else
            selectedCategory.add(category)
    }
}