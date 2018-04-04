package com.github.guenhter.webkotlin.shelve

import com.github.guenhter.webkotlin.model.Book
import com.github.guenhter.webkotlin.model.Comment

interface BookShelve {
    fun books(): List<Book>
    fun bookComments(title: String): List<Comment>
    fun addComment(title: String, comment: Comment)
}

class BookNotFoundException(title: String) : kotlin.Exception("Book $title not found")

class BookShelveImpl : BookShelve {
    var books: List<Book> = listOf(KOTLIN_IN_ACTION_BOOK)
    var comments: Map<String, List<Comment>> = books.map { it.title to emptyList<Comment>() }.toMap()

    override fun books(): List<Book> = books

    override fun bookComments(title: String): List<Comment> {
        assertTitle(title)
        return comments[title].orEmpty()
    }

    override fun addComment(title: String, comment: Comment) {
        assertTitle(title)

        if (!comments[title].orEmpty().contains(comment)) {
            comments += title to comments[title].orEmpty() + comment
        }
    }

    private fun assertTitle(title: String) {
        books.find { it.title == title } ?: throw BookNotFoundException(title)
    }
}

val KOTLIN_IN_ACTION_BOOK = Book(
        title = "Kotlin in Action",
        chapters = listOf(
            "Ch 1. Kotlin: what and why",
            "Ch 2. Kotlin basics",
            "Ch 3. Defining and calling functions",
            "Ch 4. Classes, objects, and interfaces",
            "Ch 5. Programming with lambdas",
            "Ch 6. The Kotlin type system",
            "Ch 7. Operator overloading and other conventions",
            "Ch 8. Higher-order functions: lambdas as parameters and return values",
            "Ch 9. Generics",
            "Ch 10. Annotations and reflection",
            "Ch 11. DSL construction",
            "App A. Building Kotlin projects",
            "App B. Documenting Kotlin code",
            "App C. The Kotlin ecosystem"),
        imageUrl = "img/kotlin-in-action-cover.jpg"
)