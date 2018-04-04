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
    maven(url = "https://dl.bintray.com/kotlin/ktor")
}
dependencies {
    val ktorVersion = "0.9.2"
    compile(kotlin("stdlib-jdk8"))
    "expectedBy"(project(":web-kotlin:common:model"))
    "expectedBy"(project(":web-kotlin:common:book-shelve"))
    "expectedBy"(project(":web-kotlin:common:html-common"))

    compile("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.9")
    compile("io.ktor:ktor-server-netty:$ktorVersion")
    compile("io.ktor:ktor-html-builder:$ktorVersion")
    compile("ch.qos.logback:logback-classic:1.2.3")

    testCompile(kotlin("test-junit"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}
