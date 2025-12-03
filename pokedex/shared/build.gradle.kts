import co.touchlab.skie.configuration.FlowInterop
import co.touchlab.skie.configuration.SealedInterop
import co.touchlab.skie.configuration.SuspendInterop
import com.google.devtools.ksp.gradle.KspAATask
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.com.android.kotlin.multiplatform.library)
    alias(libs.plugins.org.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.co.touchlab.skie)
    alias(libs.plugins.com.rickclephas.kmp.nativecoroutines)
    alias(libs.plugins.androidx.room)
}

kotlin {
    androidLibrary {
        namespace = "com.kfaraj.samples.pokedex.shared"
        compileSdk = 36
        minSdk = 26
        withHostTest {
        }
    }
    iosArm64()
    iosSimulatorArm64()
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "Shared"
        }
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.viewmodel.savedstate)
                implementation(libs.androidx.room.paging)
                implementation(libs.androidx.room.runtime)
                implementation(libs.androidx.sqlite.bundled)
                implementation(libs.io.insert.koin.annotations)
                implementation(libs.io.insert.koin.core)
                implementation(libs.io.insert.koin.core.viewmodel)
                implementation(libs.io.ktor.client.content.negotiation)
                implementation(libs.io.ktor.client.core)
                implementation(libs.io.ktor.serialization.kotlinx.json)
                api(libs.com.rickclephas.kmp.observableviewmodel.core)
                implementation(libs.org.jetbrains.kotlinx.coroutines.core)
                implementation(libs.org.jetbrains.kotlinx.serialization.json)
            }
            kotlin {
                srcDir("build/generated/ksp/metadata/commonMain/kotlin")
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
                implementation(libs.junit)
                implementation(libs.org.jetbrains.kotlinx.coroutines.test)
                implementation(libs.org.mockito.core)
                implementation(libs.org.mockito.kotlin)
            }
        }
        iosMain {
            dependencies {
                implementation(libs.io.ktor.client.darwin)
            }
        }
    }
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.addAll(
            "-Xexpect-actual-classes"
        )
        optIn.addAll(
            "androidx.paging.ExperimentalPagingApi",
            "kotlin.experimental.ExperimentalObjCName",
            "kotlinx.cinterop.ExperimentalForeignApi",
            "kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
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

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspCommonMainMetadata", libs.io.insert.koin.ksp.compiler)
    add("kspAndroid", libs.io.insert.koin.ksp.compiler)
    add("kspIosArm64", libs.io.insert.koin.ksp.compiler)
    add("kspIosSimulatorArm64", libs.io.insert.koin.ksp.compiler)
}

tasks.withType<KspAATask>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
