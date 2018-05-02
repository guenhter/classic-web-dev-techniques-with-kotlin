package com.github.guenhter.webkotlin.js

import com.github.guenhter.webkotlin.common.createBody
import com.github.guenhter.webkotlin.model.Book
import com.github.guenhter.webkotlin.model.Comment
import kotlinx.html.dom.append
import kotlinx.html.html
import kotlin.browser.document
import kotlin.browser.window


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

fun main(args: Array<String>) {
    val smf: dynamic = js("({})")
    smf.method = "GET"
    smf.mode   = "cors"
    smf.cache  = "default"

    window.fetch("http://localhost:8081/api/book", smf)
            .then { it.text().then<Unit> { bookResponseString ->
                val book = JSON.parse<JsBook>(bookResponseString).toBook()

                window.fetch("http://localhost:8081/api/book/${book.title}/comments", smf)
                        .then { it.text().then<Unit> { commentResponseString ->
                            val comments = JSON.parse<Array<JsComment>>(commentResponseString).toComments()

                            document.body?.append?.html {
                                createBody(book, comments, true)
                            }
                        }.catch { error -> console.log("error: $error")}
            } }
            .catch { error -> console.log("error: $error")}
            }
}
