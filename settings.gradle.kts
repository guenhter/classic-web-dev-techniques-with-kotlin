
pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "kotlin2js") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }
            if (requested.id.id == "org.jetbrains.kotlin.frontend") {
                useModule("org.jetbrains.kotlin:kotlin-frontend-plugin:${requested.version}")
            }
            if (requested.id.id == "kotlinx-serialization") {
                useModule("org.jetbrains.kotlinx:kotlinx-gradle-serialization-plugin:${requested.version}")
            }
        }
    }

    repositories {
        gradlePluginPortal()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://kotlin.bintray.com/kotlinx")
    }
}

include("web-kotlin:common:model")
include("web-kotlin:common:book-shelve")
include("web-kotlin:common:html-common")
include("web-kotlin:jvm:static-site-generation")
include("web-kotlin:jvm:books-backend")
include("web-kotlin:jvm:dynamic-server-side-generation")
include("web-kotlin:js:dynamic-client-side-generation")
include("classic:dynamic-server-side-generation-spring")
