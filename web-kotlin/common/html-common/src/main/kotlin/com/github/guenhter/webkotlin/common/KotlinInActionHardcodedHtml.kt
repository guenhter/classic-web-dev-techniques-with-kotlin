package com.github.guenhter.webkotlin.common

import kotlinx.html.*

fun HTML.hardcodedKotlinInActionHtml() {
    head {
        meta(charset = "UTF-8")
        title(content = "Kotlin in Action")
        styleLink(url = "css/style.css")
    }
    body {
        header(classes = "header") {
            h1 { +"Kotlin in Action" }
        }
        article(classes = "main") {
            h2 { +"Chapters" }
            div {
                id = "chapters"
                ol {
                    li { +"Ch 1. Kotlin: what and why" }
                    li { +"Ch 2. Kotlin basics" }
                    li { +"Ch 3. Defining and calling functions" }
                    li { +"Ch 4. Classes, objects, and interfaces" }
                    li { +"Ch 5. Programming with lambdas" }
                    li { +"Ch 6. The Kotlin type system" }
                    li { +"Ch 7. Operator overloading and other conventions" }
                    li { +"Ch 8. Higher-order functions: lambdas as parameters and return values" }
                    li { +"Ch 9. Generics" }
                    li { +"Ch 10. Annotations and reflection" }
                    li { +"Ch 11. DSL construction" }
                    li { +"App A. Building Kotlin projects" }
                    li { +"App B. Documenting Kotlin code" }
                    li { +"App C. The Kotlin ecosystem" }
                }
            }

            div {
                id = "comments"

                div { +"User Comments" }
                ol {
                    li {
                        div(classes = "comment-author") {
                            +"John Doe"
                        }
                        div(classes = "comment-author") {
                            +"Nice book!"
                        }
                    }
                }
            }
        }
        aside(classes = "aside") {
            img(classes = "cover", src = "img/kotlin-in-action-cover.jpg", alt = "Kotlin in Action - Cover")
        }
    }
}
