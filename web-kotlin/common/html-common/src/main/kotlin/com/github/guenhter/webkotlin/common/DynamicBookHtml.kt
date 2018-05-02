package com.github.guenhter.webkotlin.common

import com.github.guenhter.webkotlin.model.Book
import com.github.guenhter.webkotlin.model.Comment
import kotlinx.html.*

fun HTML.createHtmlForBook(
        book: Book,
        comments: List<Comment> = emptyList(),
        generateCommentsInput: Boolean = false
) {
    createHead(book)
    createBody(book, comments, generateCommentsInput)
}

fun HTML.createHead(book: Book) {
    head {
        meta(charset = "UTF-8")
        title(content = book.title)
        styleLink(url = "css/style.css")
    }
}

fun HTML.createBody(book: Book, comments: List<Comment>, generateCommentsInput: Boolean) {
    body {
        header(classes = "header") {
            h1 { +book.title }
        }
        article(classes = "main") {
            h2 { +"Chapters" }
            div {
                id = "chapters"
                ol {
                    book.chapters.forEach { chapter ->
                        li { +chapter }
                    }
                }
            }

            if (comments.isNotEmpty()) {
                createUserComments(comments)
            }
            if (generateCommentsInput) {
                createUserCommentInput()
            }
        }
        aside(classes = "aside") {
            img(classes = "cover", src = book.imageUrl, alt = "${book.title} - Cover")
        }
    }
}

private fun ARTICLE.createUserComments(comments: List<Comment>) {
    div {
        id = "comments"

        div { +"User Comments" }
        ol {
            comments.forEach { comment ->
                createUserComment(comment)
            }
        }
    }
}

private fun OL.createUserComment(comment: Comment) {
    li {
        div(classes = "comment-author") {
            +comment.author
        }
        div(classes = "user-comment") {
            +comment.comment
        }
    }
}

private fun ARTICLE.createUserCommentInput() {
    form {
        id = "commentForm"

        div(classes = "input-username") { +"Name" }
        input(type = InputType.text, name = "username") { }

        div(classes = "input-comment")  { +"Comment" }
        input(type = InputType.text, name = "usercomment") { }

        input(type = InputType.submit) { value = "Submit" }
    }
}
