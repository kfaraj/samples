plugins {
    alias(libs.plugins.com.android.kotlin.multiplatform.library)
    alias(libs.plugins.org.jetbrains.compose)
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.io.insert.koin.compiler.plugin)
}

kotlin {
    android {
        namespace = "com.kfaraj.samples.pokedex.feature.pokemon"
        compileSdk = 37
        minSdk = 28
        androidResources {
            enable = true
        }
        withHostTest {
            isIncludeAndroidResources = true
        }
    }
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        commonMain {
            dependencies {
                implementation(project(":pokedex:core:ui"))
                implementation(project(":pokedex:data:pokemon"))
                implementation(libs.androidx.lifecycle.runtime)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.androidx.lifecycle.viewmodel)
                api(libs.androidx.navigation3.runtime)
                implementation(libs.androidx.paging.common)
                implementation(libs.androidx.paging.compose)
                api(libs.com.rickclephas.kmp.observableviewmodel.core)
                implementation(libs.io.coil.compose)
                implementation(libs.io.coil.network.ktor)
                implementation(libs.io.insert.koin.annotations)
                implementation(libs.io.insert.koin.compose.viewmodel)
                implementation(libs.io.insert.koin.core)
                api(libs.org.jetbrains.androidx.navigation3.ui)
                api(libs.org.jetbrains.compose.components.resources)
                api(libs.org.jetbrains.compose.material3)
                implementation(libs.org.jetbrains.compose.ui.tooling.preview)
                implementation(libs.org.jetbrains.kotlinx.coroutines.core)
                implementation(libs.org.jetbrains.kotlinx.serialization.json)
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
    }
    jvmToolchain(21)
    explicitApi()
    compilerOptions {
        optIn.addAll(
            "androidx.compose.material3.ExperimentalMaterial3Api",
            "androidx.paging.ExperimentalPagingApi",
            "kotlinx.cinterop.ExperimentalForeignApi",
            "kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

compose {
    resources {
        publicResClass = true
        packageOfResClass = "com.kfaraj.samples.pokedex.feature.pokemon"
    }
}

dependencies {
    add("androidRuntimeClasspath", libs.org.jetbrains.compose.ui.tooling)
}
