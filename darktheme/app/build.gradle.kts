plugins {
    alias(libs.plugins.com.android.application)
}

android {
    namespace = "com.kfaraj.samples.darktheme"
    compileSdk = 37
    defaultConfig {
        applicationId = "com.kfaraj.samples.darktheme"
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
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(libs.androidx.activity)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.coordinatorlayout)
    implementation(libs.androidx.core)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.preference)
    implementation(libs.com.google.android.material)
}
