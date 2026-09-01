plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.io.insert.koin.compiler.plugin)
}

android {
    namespace = "com.kfaraj.samples.koin.multimodule.data"
    compileSdk = 37
    defaultConfig {
        minSdk = 28
    }
}

kotlin {
    jvmToolchain(21)
    explicitApi()
}

dependencies {
    implementation(libs.io.insert.koin.android)
    implementation(libs.io.insert.koin.annotations)
}
