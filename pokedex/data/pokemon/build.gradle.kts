plugins {
    alias(libs.plugins.com.android.kotlin.multiplatform.library)
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.androidx.room3)
    alias(libs.plugins.io.insert.koin.compiler.plugin)
}

kotlin {
    android {
        namespace = "com.kfaraj.samples.pokedex.data.pokemon"
        compileSdk = 37
        minSdk = 28
        withHostTest {
        }
    }
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        commonMain {
            dependencies {
                api(libs.androidx.paging.common)
                implementation(libs.androidx.room3.paging)
                implementation(libs.androidx.room3.runtime)
                implementation(libs.androidx.sqlite.bundled)
                implementation(libs.io.insert.koin.annotations)
                implementation(libs.io.insert.koin.core)
                implementation(libs.io.ktor.client.content.negotiation)
                implementation(libs.io.ktor.client.core)
                implementation(libs.io.ktor.serialization.kotlinx.json)
                api(libs.org.jetbrains.kotlinx.coroutines.core)
                implementation(libs.org.jetbrains.kotlinx.serialization.json)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.io.ktor.client.okhttp)
            }
        }
        named("androidHostTest") {
            dependencies {
                implementation(libs.androidx.paging.testing)
                implementation(libs.io.mockk)
                implementation(libs.junit)
                implementation(libs.org.jetbrains.kotlinx.coroutines.test)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.io.ktor.client.darwin)
            }
        }
    }
    jvmToolchain(21)
    explicitApi()
    compilerOptions {
        freeCompilerArgs.addAll(
            "-Xexpect-actual-classes"
        )
        optIn.addAll(
            "androidx.paging.ExperimentalPagingApi",
            "kotlinx.cinterop.ExperimentalForeignApi"
        )
    }
}

room3 {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspAndroid", libs.androidx.room3.compiler)
    add("kspIosArm64", libs.androidx.room3.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room3.compiler)
}
