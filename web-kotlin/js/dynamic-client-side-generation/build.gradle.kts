import org.jetbrains.kotlin.gradle.frontend.KotlinFrontendExtension
import org.jetbrains.kotlin.gradle.frontend.npm.NpmExtension
import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackBundler
import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

buildscript {
    repositories {
        jcenter()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.41")
        classpath("org.jetbrains.kotlin:kotlin-frontend-plugin:0.0.30")
    }
}

apply {
    plugin("kotlin-platform-js")
    plugin("kotlin2js")
}

repositories {
    jcenter()
}

dependencies {
    "compile"(kotlin("stdlib-js"))
    "expectedBy"(project(":web-kotlin:common:html-common"))
    "expectedBy"(project(":web-kotlin:common:html-common"))
    "compile"("org.jetbrains.kotlinx:kotlinx-html-js:0.6.9")

    "testCompile"(kotlin("test-js"))
}

tasks.withType<Kotlin2JsCompile> {
    kotlinOptions.metaInfo = true
    kotlinOptions.outputFile = "${project.buildDir.path}/js/${project.name}.js"
    kotlinOptions.sourceMap = true
    kotlinOptions.moduleKind = "commonjs"
    kotlinOptions.main = "call"
}
