plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
}

android {
    namespace = "com.kfaraj.samples.darktheme"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.kfaraj.samples.darktheme"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "0.1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    signingConfigs {
        register("release") {
            val storePath = properties["signingStorePath"] as String?
            storeFile = if (storePath != null) file(storePath) else null
            storePassword = properties["signingStorePassword"] as String?
            keyAlias = properties["signingKeyAlias"] as String?
            keyPassword = properties["signingKeyPassword"] as String?
        }
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.preference.ktx)
    implementation(libs.com.google.android.material)
}
