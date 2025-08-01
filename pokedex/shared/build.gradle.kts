plugins {
    alias(libs.plugins.com.android.kotlin.multiplatform.library)
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.com.rickclephas.kmp.nativecoroutines)
}

kotlin {
    androidLibrary {
        namespace = "com.kfaraj.samples.pokedex.shared"
        compileSdk = 35
        minSdk = 26
        withHostTestBuilder {
        }
        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
        }
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.io.ktor.client.content.negotiation)
                implementation(libs.io.ktor.client.core)
                implementation(libs.io.ktor.serialization.kotlinx.json)
                implementation(libs.com.rickclephas.kmp.observableviewmodel.core)
                implementation(libs.org.jetbrains.kotlinx.coroutines.core)
                implementation(libs.org.jetbrains.kotlinx.serialization.json)
            }
        }
        commonTest {
            dependencies {
                implementation(libs.org.jetbrains.kotlin.test)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.io.ktor.client.okhttp)
            }
        }
        getByName("androidHostTest") {
            dependencies {
            }
        }
        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.androidx.test.core.ktx)
                implementation(libs.androidx.test.rules)
                implementation(libs.androidx.test.runner)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.io.ktor.client.darwin)
            }
        }
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }
    }
    jvmToolchain(21)
}
