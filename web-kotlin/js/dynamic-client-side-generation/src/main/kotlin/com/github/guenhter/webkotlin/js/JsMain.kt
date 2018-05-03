package com.github.guenhter.webkotlin.js

import com.github.guenhter.webkotlin.common.createBody
import com.github.guenhter.webkotlin.model.Book
import com.github.guenhter.webkotlin.model.Comment
import kotlinx.html.dom.append
import kotlinx.html.html
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.get
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    fetchAndParseJson<JsBook>("http://localhost:8081/api/book") {
        val book = it.toBook()

        fetchAndParseJson<Array<JsComment>>("http://localhost:8081/api/book/${book.title}/comments") {
            val comments = it.toComments()
            createHtmlContent(book, comments)
        }
    }
}

private inline fun <T> fetchAndParseJson(url: String, crossinline block: (T) -> Unit) {
    val smf: dynamic = js("({})")
    smf.method = "GET"
    smf.mode = "cors"
    smf.cache = "default"

    window.fetch(url, smf)
            .then {
                it.text().then { responseString ->
                    val result = JSON.parse<T>(responseString)

                    block(result)
                }.catch { error -> console.log("error: $error") }
            }
}

private fun createHtmlContent(book: Book, comments: List<Comment>) {
    document.body?.append?.html {
        createBody(book, comments, true)
    }

    val submitButton = document.querySelector("#commentForm input[type=submit]") as HTMLInputElement
    submitButton.type = "button" // avoid, that form submits and reloads the page
    submitButton.onclick = { _ ->
        val userName = document.getElementsByName("username")[0] as HTMLInputElement
        val userComment = document.getElementsByName("usercomment")[0] as HTMLInputElement

        println("Submitting comment '${userComment.value}' from user '${userName.value}'")
    }
}
