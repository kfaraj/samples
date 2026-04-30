plugins {
    alias(libs.plugins.com.android.kotlin.multiplatform.library)
    alias(libs.plugins.org.jetbrains.compose)
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
}

kotlin {
    android {
        namespace = "com.kfaraj.samples.pokedex.core.ui"
        compileSdk = 36
        minSdk = 26
        androidResources {
            enable = true
        }
        withHostTest {
        }
    }
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.org.jetbrains.compose.components.resources)
                implementation(libs.org.jetbrains.compose.material3)
                implementation(libs.org.jetbrains.compose.ui.tooling.preview)
            }
        }
        named("androidHostTest") {
            dependencies {
                implementation(libs.junit)
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
