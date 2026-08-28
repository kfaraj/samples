plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
    alias(libs.plugins.io.insert.koin.compiler.plugin)
}

android {
    namespace = "com.kfaraj.samples.pokedex"
    compileSdk = 37
    defaultConfig {
        applicationId = "com.kfaraj.samples.pokedex"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "0.1.0"
    }
    signingConfigs {
        register("release") {
            storeFile = findProperty("signingStoreFile")?.let { file(it) }
            storePassword = findProperty("signingStorePassword") as? String
            keyAlias = findProperty("signingKeyAlias") as? String
            keyPassword = findProperty("signingKeyPassword") as? String
        }
    }
    buildTypes {
        named("release") {
            optimization {
                enable = true
            }
            signingConfig = signingConfigs.getByName("release")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

kotlin {
    jvmToolchain(21)
}

koinCompiler {
    compileSafety = false
}

dependencies {
    implementation(project(":pokedex:core:ui"))
    implementation(project(":pokedex:feature:pokemon"))
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.io.insert.koin.android)
    implementation(libs.io.insert.koin.annotations)
    testImplementation(libs.androidx.test.core.ktx)
    testImplementation(libs.androidx.test.ext.junit.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.org.robolectric)
}
