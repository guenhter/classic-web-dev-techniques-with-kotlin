package com.github.guenhter.webkotlin.model

data class Book(val title: String, val chapters: List<String>, val imageUrl: String)
data class Comment(val author: String, val comment: String)
