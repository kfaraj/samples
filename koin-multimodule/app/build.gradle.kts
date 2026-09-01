plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.io.insert.koin.compiler.plugin)
}

android {
    namespace = "com.kfaraj.samples.koin.multimodule"
    compileSdk = 37
    defaultConfig {
        applicationId = "com.kfaraj.samples.koin.multimodule"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "0.1.0"
    }
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(project(":koin-multimodule:feature"))
    implementation(libs.androidx.activity)
    implementation(libs.io.insert.koin.android)
    implementation(libs.io.insert.koin.annotations)
}
