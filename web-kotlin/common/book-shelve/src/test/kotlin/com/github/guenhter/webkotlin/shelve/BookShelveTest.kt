package com.github.guenhter.webkotlin.shelve

import com.github.guenhter.webkotlin.model.Comment
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.Test
import kotlin.test.fail

class BookShelveTest {
    private var title = "Kotlin in Action"
    private var store = BookShelveImpl()

    @Test
    fun `it gets the book contained in the store`() {
        assertEquals(1, store.books().size)
        assertEquals(title, store.books().first().title)
    }

    @Test
    fun `book has no comments`() {
        assertTrue(store.bookComments(title).isEmpty())
    }

    @Test
    fun `after adding a comment, it is also returned when requesting the comments`() {
        var addedComments = emptyList<Comment>()

        (1..10).forEach {
            val comment = Comment(author = "Me", comment = "Comment $it")
            addedComments += comment

            store.addComment(title, comment)

            assertEquals(addedComments, store.bookComments(title))
        }
    }

    @Test
    fun `the same user can post the same comment only once`() {
        store.addComment(title, Comment(author = "Me", comment = "Some comment"))
        store.addComment(title, Comment(author = "Me", comment = "Some comment"))

        assertEquals(1, store.bookComments(title).size)
    }

    @Test
    fun `get comments - wrong book title gets an exception`() {
        try {
            store.bookComments(title = "Not existing title")
            fail("Should have thrown BookNotFoundException")
        } catch (e: BookNotFoundException) {
            // this is expected
        }
    }

    @Test
    fun `add comment - wrong book title gets an exception`() {
        try {
            store.addComment(title = "Not existing title", comment = Comment("me", "some comment"))
            store.bookComments(title = "Not existing title")
            fail("Should have thrown BookNotFoundException")
        } catch (e: BookNotFoundException) {
            // this is expected
        }
    }
}