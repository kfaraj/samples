import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.com.android.kotlin.multiplatform.library)
    alias(libs.plugins.org.jetbrains.compose)
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
    alias(libs.plugins.io.insert.koin.compiler.plugin)
}

kotlin {
    android {
        namespace = "com.kfaraj.samples.pokedex.shared"
        compileSdk = 37
        minSdk = 28
    }
    iosArm64()
    iosSimulatorArm64()
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries {
            framework {
                baseName = "Shared"
                export(project(":pokedex:feature:pokemon"))
            }
        }
    }
    sourceSets {
        commonMain {
            dependencies {
                api(project(":pokedex:core:ui"))
                api(project(":pokedex:feature:pokemon"))
            }
        }
        iosMain {
            dependencies {
                implementation(libs.androidx.lifecycle.viewmodel.navigation3)
                implementation(libs.io.insert.koin.annotations)
                implementation(libs.io.insert.koin.core)
            }
        }
    }
    jvmToolchain(21)
    explicitApi()
}
