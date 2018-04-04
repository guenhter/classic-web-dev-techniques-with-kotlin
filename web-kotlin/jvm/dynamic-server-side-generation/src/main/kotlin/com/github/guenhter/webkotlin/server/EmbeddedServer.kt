package com.github.guenhter.webkotlin.server

import com.github.guenhter.webkotlin.common.createHtmlForBook
import com.github.guenhter.webkotlin.model.Comment
import com.github.guenhter.webkotlin.shelve.BookShelve
import com.github.guenhter.webkotlin.shelve.BookShelveImpl
import com.github.guenhter.webkotlin.shelve.KOTLIN_IN_ACTION_BOOK
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.content.files
import io.ktor.content.static
import io.ktor.html.respondHtml
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

private val bookShelve: BookShelve = BookShelveImpl()

fun main(args: Array<String>) {
    runEmbeddedServer()
}

fun runEmbeddedServer() {
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            static("css") {
                files(folder = "resources/css")
            }
            static("img") {
                files(folder = "resources/img")
            }
            get(path = "/") {
                call.respondHtml {
                    val book = KOTLIN_IN_ACTION_BOOK

                    addCommentIfAvailable(call, book.title)

                    // retrieve the comments on every request
                    val comments = bookShelve.bookComments(book.title)

                    // generate the html with the comments on every request
                    createHtmlForBook(book, comments, true)
                }
            }
        }
    }
    server.start(wait = true)
}

private fun addCommentIfAvailable(call: ApplicationCall, bookTitle: String) {
    val userName = call.request.queryParameters["username"] ?: ""
    val comment = call.request.queryParameters["usercomment"] ?: ""

    if (userName.isNotEmpty() && comment.isNotEmpty()) {
        bookShelve.addComment(bookTitle, Comment(userName, comment))
    }
}
