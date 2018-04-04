package com.github.guenhter.webkotlin.static

import com.github.guenhter.webkotlin.common.createHtmlForBook
import com.github.guenhter.webkotlin.shelve.KOTLIN_IN_ACTION_BOOK
import kotlinx.html.html
import kotlinx.html.stream.appendHTML
import java.io.File
import java.io.FileWriter

private var rootOutputFolder = File("build/assets")
private var indexHtmlFile = File(rootOutputFolder,"index.html")

fun main(args: Array<String>) {
    exportHtmlToFile()
}

private fun exportHtmlToFile() {
    rootOutputFolder.mkdirs()

    FileWriter(indexHtmlFile).use {
        it.appendHTML().html {
//            hardcodedKotlinInActionHtml()
            createHtmlForBook(KOTLIN_IN_ACTION_BOOK)
        }
    }
}

