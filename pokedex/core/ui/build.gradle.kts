plugins {
    alias(libs.plugins.com.android.kotlin.multiplatform.library)
    alias(libs.plugins.org.jetbrains.compose)
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
}

kotlin {
    android {
        namespace = "com.kfaraj.samples.pokedex.core.ui"
        compileSdk = 37
        minSdk = 28
        androidResources {
            enable = true
        }
    }
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        commonMain {
            dependencies {
                api(libs.org.jetbrains.compose.components.resources)
                api(libs.org.jetbrains.compose.material3)
                implementation(libs.org.jetbrains.compose.ui.tooling.preview)
            }
        }
    }
    jvmToolchain(21)
    explicitApi()
}

compose {
    resources {
        publicResClass = true
        packageOfResClass = "com.kfaraj.samples.pokedex.core.ui"
    }
}

dependencies {
    add("androidRuntimeClasspath", libs.org.jetbrains.compose.ui.tooling)
}
