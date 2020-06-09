package com.example.model

enum class Category(val categoryName: String, val categoryId: String) {
    Android("안드로이드", "Android"),
    Ios("ios", "IOS"),
    Web("웹 프론드", "WebFront"),
    Window("윈도우", "Window"),
    Server("서버", "Server"),
    Embedded("임베디드 소프트웨어", "Embedded"),
    Machine("머신러닝", "MachineLearning"),
    Design("디자인", "Design"),
    Db("데이터베이스", "Database"),
    Planner("기획자", "Plan"),
    Game("게임 개발", "Game"),
}