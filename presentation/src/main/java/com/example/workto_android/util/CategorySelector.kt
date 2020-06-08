package com.example.workto_android.util

import com.example.model.Category

interface CategorySelector {
    fun selectCategory(category: Category)
}