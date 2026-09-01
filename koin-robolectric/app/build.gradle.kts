plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.io.insert.koin.compiler.plugin)
}

android {
    namespace = "com.kfaraj.samples.koin.robolectric"
    compileSdk = 37
    defaultConfig {
        applicationId = "com.kfaraj.samples.koin.robolectric"
        minSdk = 28
        targetSdk = 37
        versionCode = 1
        versionName = "0.1.0"
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

dependencies {
    implementation(libs.androidx.activity)
    implementation(libs.io.insert.koin.android)
    implementation(libs.io.insert.koin.annotations)
    testImplementation(libs.androidx.test.core.ktx)
    testImplementation(libs.androidx.test.ext.junit.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.org.robolectric)
}
