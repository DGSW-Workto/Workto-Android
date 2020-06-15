package com.example.data

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class DateTimeConverter {
    @SuppressLint("SimpleDateFormat")
    fun jsonTimeToTime(jsonTime: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("yyyy년 MM월 dd일")
        return formatter.format(parser.parse(jsonTime)!!)
    }
}