import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URL

plugins {
    kotlin("jvm") version "1.2.41"
}

apply {
    plugin("kotlin-platform-jvm")
}

repositories {
    jcenter()
}
dependencies {
    compile(kotlin("stdlib-jdk8"))
    "expectedBy"(project(":web-kotlin:common:html-common"))
    "expectedBy"(project(":web-kotlin:common:book-shelve"))

    compile("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.9")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}
