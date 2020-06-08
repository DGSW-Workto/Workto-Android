package com.example.workto_android.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.login.JoinUseCase
import com.example.domain.result.Event
import com.example.model.Category
import com.example.model.JoinParameter
import com.example.workto_android.ui.BaseViewModel
import com.example.workto_android.util.CategorySelector

class JoinViewModel(private val joinUseCase: JoinUseCase) : BaseViewModel(), CategorySelector {

    var isLastPage = false

    val id = ObservableField("")
    val email = ObservableField("")
    val nickname = ObservableField("")
    val password = ObservableField("")
    val passwordCheck = ObservableField("")
    var isEquals = false

    private val _editTextEnabled = MutableLiveData(true)
    val editTextEnabled: LiveData<Boolean>
        get() = _editTextEnabled

    private val _buttonEnabled = MutableLiveData(false)
    val buttonEnabled: LiveData<Boolean>
        get() = _buttonEnabled

    private val _buttonText = MutableLiveData<String>("다음")
    val buttonText: LiveData<String>
        get() = _buttonText

    private val _titleText = MutableLiveData("기본 정보를 입력해주세요")
    val titleText: LiveData<String>
        get() = _titleText

    private val _showCategory = MutableLiveData<Unit>()
    val showCategory: LiveData<Unit>
        get() = _showCategory

    private val allSelectedCategory = arrayListOf<Category>()
    val categoryList: ArrayList<Category> = Category.values().toCollection(ArrayList())

    private val _selectedCategory = MutableLiveData<Pair<Int, ArrayList<Category>>>()
    val selectedCategory: LiveData<Pair<Int, ArrayList<Category>>>
        get() = _selectedCategory

    private val joinResult = joinUseCase.observe()

    private val _completeJoin = MediatorLiveData<Event<Unit>>()
    val completeJoin: LiveData<Event<Unit>>
        get() = _completeJoin

    init {
        joinResult.onSuccess(_completeJoin) {
            _completeJoin.value = Event(Unit)
        }

        joinResult.onError(_error) {
            _error.value = Event(it)
        }
    }

    fun onButtonClick() {
        if (isLastPage) {
            this(
                joinUseCase(
                    JoinParameter(
                        getSkills(),
                        email.get()!!,
                        id.get()!!,
                        password.get()!!,
                        nickname.get()!!
                    )
                )
            )
        } else {
            setLast()
        }
    }

    fun setFirst() {
        _titleText.value = "기본 정보를 입력해주세요"
        _buttonText.value = "다음"
        _editTextEnabled.value = true
        _buttonEnabled.value = true
        isLastPage = false
    }

    private fun setLast() {
        _titleText.value = "관심 분야를 선택해주세요 (최대 3개)"
        _buttonText.value = "시작하기"
        _buttonEnabled.value = false
        _editTextEnabled.value = false
        _showCategory.value = Unit
        isLastPage = true
    }

    override fun selectCategory(category: Category) {
        if (allSelectedCategory.contains(category))
            allSelectedCategory.remove(category)
        else {
            if (allSelectedCategory.size >= 3) {
                _error.value = Event("최대 3개까지 선택 가능합니다")
                return
            }
            allSelectedCategory.add(category)
        }
        _buttonEnabled.value = allSelectedCategory.size > 0

        val position = categoryList.indexOf(category)
        _selectedCategory.value = Pair(position, allSelectedCategory)

    }

    fun onEditTextChanged() {
        _buttonEnabled.value =
            id.get()!!.isNotBlank() && password.get()!!.isNotBlank() && email.get()!!
                .isNotBlank() && nickname.get()!!.isNotBlank() && passwordCheck.get()!!
                .isNotBlank() && isEquals
    }

    private fun getSkills(): ArrayList<String> {
        return with(allSelectedCategory) {
            ArrayList(
                this.map { it.categoryName }
            )
        }
    }

}