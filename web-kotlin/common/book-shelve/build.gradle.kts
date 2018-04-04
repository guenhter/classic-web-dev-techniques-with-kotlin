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
    jcenter()
}

val kotlinVersion = "1.2.41"

dependencies {
    "compile"(kotlin("stdlib-common:"))
    "compile"(project(":web-kotlin:common:model"))

    "testCompile"(kotlin("test-annotations-common"))
    "testCompile"(kotlin("test-common"))
}
