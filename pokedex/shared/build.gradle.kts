plugins {
    alias(libs.plugins.com.android.kotlin.multiplatform.library)
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
}

kotlin {
    android {
        namespace = "com.kfaraj.samples.pokedex.shared"
        compileSdk = 37
        minSdk = 28
    }
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        commonMain {
            dependencies {
                api(project(":pokedex:core:ui"))
                api(project(":pokedex:feature:pokemon"))
            }
        }
    }
    jvmToolchain(21)
    explicitApi()
}
