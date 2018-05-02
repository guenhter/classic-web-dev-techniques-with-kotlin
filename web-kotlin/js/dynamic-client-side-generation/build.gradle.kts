import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile


plugins {
    val kotlinVersion = "1.2.41"

    id("kotlin2js") version kotlinVersion
}

apply {
    plugin("kotlin-platform-js")
}

repositories {
    jcenter()
}

dependencies {
    "compile"(kotlin("stdlib-js"))
    "expectedBy"(project(":web-kotlin:common:model"))
    "expectedBy"(project(":web-kotlin:common:html-common"))
    "compile"("org.jetbrains.kotlinx:kotlinx-html-js:0.6.9")

    "testCompile"(kotlin("test-js"))
}

//tasks.withType<Kotlin2JsCompile> {
//    kotlinOptions.metaInfo = true
//    kotlinOptions.outputFile = "${project.buildDir.path}/js/${project.name}.js"
//    kotlinOptions.sourceMap = true
//    kotlinOptions.moduleKind = "commonjs"
//    kotlinOptions.main = "call"
//}

val mainSourceSet = the<JavaPluginConvention>().sourceSets["main"]!!


val webPath = "web"
val webOutputDir = "$buildDir/$webPath"
val jsOutputDir = "$webOutputDir/js"

tasks {
    "compileKotlin2Js"(Kotlin2JsCompile::class) {
        kotlinOptions {
            outputFile = "$jsOutputDir/${project.name}.js"
            sourceMap = true
            sourceMapEmbedSources = "always"
            moduleKind = "umd"
            metaInfo = true
        }
    }
    "build" {
        doLast {
            configurations["compileClasspath"].forEach { file ->
                copy {
                    includeEmptyDirs = false

                    from(zipTree(file.absolutePath))
                    into(jsOutputDir)
                    include { fileTreeElement ->
                        val path = fileTreeElement.path
                        path.endsWith(".js")
                                && (path.startsWith("META-INF/resources/") || !path.startsWith("META-INF/"))
                    }
                }
            }
            copy {
                from(mainSourceSet.resources.srcDirs)
                into(webOutputDir)
            }
            copy {
                from(fileTree("../../../resources"))
                into(webOutputDir)
            }
        }
    }
}