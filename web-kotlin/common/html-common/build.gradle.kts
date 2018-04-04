buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.41")
    }
}

apply {
    plugin("kotlin-platform-common")
}

repositories {
    mavenCentral()
    maven(url = "https://dl.bintray.com/kotlin/kotlinx.html")
}

val kotlinVersion = "1.2.41"
val kotlinHtmlVersion = "0.6.9"

dependencies {
    "compile"("org.jetbrains.kotlin:kotlin-stdlib-common:$kotlinVersion")
    "compile"("org.jetbrains.kotlinx:kotlinx-html-common:$kotlinHtmlVersion")
    "compile"(project(":web-kotlin:common:model"))

    "testCompile"("org.jetbrains.kotlin:kotlin-test-common:$kotlinVersion")
}