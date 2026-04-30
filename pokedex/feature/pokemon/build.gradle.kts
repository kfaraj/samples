plugins {
    alias(libs.plugins.com.android.kotlin.multiplatform.library)
    alias(libs.plugins.org.jetbrains.compose)
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.com.google.devtools.ksp)
}

kotlin {
    android {
        namespace = "com.kfaraj.samples.pokedex.feature.pokemon"
        compileSdk = 36
        minSdk = 26
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
                implementation(libs.androidx.lifecycle.viewmodel.savedstate)
                implementation(libs.androidx.paging.common)
                implementation(libs.androidx.paging.compose)
                implementation(libs.io.coil.compose)
                implementation(libs.io.coil.network.ktor)
                implementation(libs.io.insert.koin.annotations)
                implementation(libs.io.insert.koin.compose.viewmodel)
                implementation(libs.io.insert.koin.core)
                implementation(libs.org.jetbrains.androidx.lifecycle.viewmodel.compose)
                implementation(libs.org.jetbrains.androidx.navigation.compose)
                implementation(libs.org.jetbrains.compose.components.resources)
                implementation(libs.org.jetbrains.compose.material3)
                implementation(libs.org.jetbrains.compose.ui.tooling.preview)
                implementation(libs.org.jetbrains.kotlinx.coroutines.core)
                implementation(libs.org.jetbrains.kotlinx.serialization.json)
            }
        }
        named("androidHostTest") {
            dependencies {
                implementation(libs.androidx.paging.testing)
                implementation(libs.androidx.test.core.ktx)
                implementation(libs.androidx.test.ext.junit.ktx)
                implementation(libs.io.mockk)
                implementation(libs.junit)
                implementation(libs.org.jetbrains.kotlinx.coroutines.test)
                implementation(libs.org.robolectric)
            }
        }
    }
    jvmToolchain(21)
    explicitApi()
    compilerOptions {
        optIn.addAll(
            "androidx.compose.material3.ExperimentalMaterial3Api",
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
    add("kspAndroid", libs.io.insert.koin.ksp.compiler)
    add("kspIosArm64", libs.io.insert.koin.ksp.compiler)
    add("kspIosSimulatorArm64", libs.io.insert.koin.ksp.compiler)
}
