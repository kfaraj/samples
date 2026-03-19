plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "com.kfaraj.samples.pokedex"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.kfaraj.samples.pokedex"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "0.1.0"
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
    buildFeatures {
        compose = true
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
    implementation(project(":pokedex:core:ui"))
    implementation(project(":pokedex:feature:pokemon"))
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.io.insert.koin.android)
    implementation(libs.io.insert.koin.androidx.compose)
    implementation(libs.io.insert.koin.annotations)
    ksp(libs.io.insert.koin.ksp.compiler)
    testImplementation(libs.androidx.test.core.ktx)
    testImplementation(libs.androidx.test.ext.junit.ktx)
    testImplementation(libs.junit)
    testImplementation(libs.org.robolectric)
}
