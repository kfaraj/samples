plugins {
    alias(libs.plugins.com.android.kotlin.multiplatform.library)
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.androidx.room)
}

kotlin {
    android {
        namespace = "com.kfaraj.samples.pokedex.data.pokemon"
        compileSdk = 36
        minSdk = 26
        withHostTest {
        }
    }
    iosArm64()
    iosSimulatorArm64()
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.androidx.paging.common)
                implementation(libs.androidx.room.paging)
                implementation(libs.androidx.room.runtime)
                implementation(libs.androidx.sqlite.bundled)
                implementation(libs.io.insert.koin.annotations)
                implementation(libs.io.insert.koin.core)
                implementation(libs.io.ktor.client.content.negotiation)
                implementation(libs.io.ktor.client.core)
                implementation(libs.io.ktor.serialization.kotlinx.json)
                implementation(libs.org.jetbrains.kotlinx.coroutines.core)
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

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspAndroid", libs.io.insert.koin.ksp.compiler)
    add("kspIosArm64", libs.io.insert.koin.ksp.compiler)
    add("kspIosSimulatorArm64", libs.io.insert.koin.ksp.compiler)
}
