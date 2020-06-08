package com.example.model

enum class Category(val categoryName: String, val categoryId: Int) {
    Android("안드로이드", 0),
    Ios("ios", 1),
    Web("웹 프론드", 2),
    Window("윈도우", 3),
    Server("서버", 4),
    Embedded("임베디드 소프트웨어", 5),
    Machine("머신러닝", 6),
    Design("디자인", 7),
    Db("데이터베이스", 8),
    Planner("기획자", 9),
}