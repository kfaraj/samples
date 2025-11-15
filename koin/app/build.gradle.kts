plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "com.kfaraj.samples.koin"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.kfaraj.samples.koin"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "0.1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
        managedDevices {
            localDevices {
                register("mediumPhoneApi36") {
                    device = "Medium Phone"
                    apiLevel = 36
                    systemImageSource = "aosp-atd"
                }
            }
        }
    }
}

kotlin {
    jvmToolchain(21)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}

dependencies {
    implementation(libs.androidx.activity)
    implementation(libs.androidx.core.ktx)
    implementation(libs.io.insert.koin.android)
    implementation(libs.io.insert.koin.annotations)
    ksp(libs.io.insert.koin.ksp.compiler)
    testImplementation(libs.androidx.test.core.ktx)
    testImplementation(libs.androidx.test.ext.junit.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.org.robolectric)
    androidTestImplementation(libs.androidx.test.core.ktx)
    androidTestImplementation(libs.androidx.test.ext.junit.ktx)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.runner)
}
