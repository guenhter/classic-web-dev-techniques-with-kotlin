package com.github.guenhter.webkotlin.js

import com.github.guenhter.webkotlin.model.Book
import com.github.guenhter.webkotlin.model.Comment

// Kotlin problem with JS, that the Kotlin classes currently can't be used for deserialization
// https://stackoverflow.com/questions/44715920/kotlin-js-json-deserialization
external interface JsBook {
    val title: String
    val chapters: Array<String>
    val imageUrl: String
}
external interface JsComment {
    val author: String
    val comment: String
}

fun JsBook.toBook(): Book = Book(title = title, chapters = chapters.toList(), imageUrl =  imageUrl)
fun JsComment.toComment(): Comment = Comment(author = author, comment = comment)
fun Array<JsComment>.toComments(): List<Comment> = map { it.toComment() }
