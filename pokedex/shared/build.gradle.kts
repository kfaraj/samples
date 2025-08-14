import co.touchlab.skie.configuration.FlowInterop
import co.touchlab.skie.configuration.SealedInterop
import co.touchlab.skie.configuration.SuspendInterop

plugins {
    alias(libs.plugins.com.android.kotlin.multiplatform.library)
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.co.touchlab.skie)
    alias(libs.plugins.com.rickclephas.kmp.nativecoroutines)
    alias(libs.plugins.app.cash.sqldelight)
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
                implementation(libs.app.cash.sqldelight.coroutines.extensions)
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
                implementation(libs.app.cash.sqldelight.android.driver)
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
                implementation(libs.app.cash.sqldelight.native.driver)
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

skie {
    features {
        group {
            SealedInterop.Enabled(true)
            SuspendInterop.Enabled(false)
            FlowInterop.Enabled(false)
        }
    }
}

sqldelight {
    databases {
        create("ApplicationDatabase") {
            packageName.set("com.kfaraj.samples.pokedex.shared.data.local")
            generateAsync.set(true)
        }
    }
}
