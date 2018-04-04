package com.github.guenhter.webkotlin.backend

import com.github.guenhter.webkotlin.model.Comment
import com.github.guenhter.webkotlin.shelve.BookShelve
import com.github.guenhter.webkotlin.shelve.BookShelveImpl
import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

private val bookShelve: BookShelve = BookShelveImpl()
private val gson = Gson()

fun main(args: Array<String>) {
    runEmbeddedServer()
}

fun runEmbeddedServer() {
    val server = embeddedServer(Netty, port = 8081) {
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }
        routing {
            get(path = "/api/book") {
                val book = bookShelve.books().first()
                call.respond(book)
            }
            get(path = "/api/book/{title}/comments") {
                val title = call.parameters["title"]

                if (title == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    val comments = bookShelve.bookComments(title)
                    call.respond(comments)
                }
            }
            post(path = "/api/book/{title}/comments") {
                val title = call.parameters["title"]

                if (title == null) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    val comment = gson.fromJson(call.receiveText(), Comment::class.java)
                    bookShelve.addComment(title, comment)
                }
            }
        }
    }
    server.start(wait = true)
}