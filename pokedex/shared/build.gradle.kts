import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.com.android.kotlin.multiplatform.library)
    alias(libs.plugins.org.jetbrains.compose)
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
    alias(libs.plugins.com.google.devtools.ksp)
}

kotlin {
    android {
        namespace = "com.kfaraj.samples.pokedex.feature.shared"
        compileSdk = 36
        minSdk = 26
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
                api(project(":pokedex:feature:pokemon"))
                implementation(project(":pokedex:core:ui"))
                implementation(libs.io.insert.koin.annotations)
                implementation(libs.io.insert.koin.core)
                implementation(libs.org.jetbrains.compose.components.resources)
                implementation(libs.org.jetbrains.compose.material3)
            }
        }
    }
    jvmToolchain(21)
    explicitApi()
}

dependencies {
    add("kspAndroid", libs.io.insert.koin.ksp.compiler)
    add("kspIosArm64", libs.io.insert.koin.ksp.compiler)
    add("kspIosSimulatorArm64", libs.io.insert.koin.ksp.compiler)
}
