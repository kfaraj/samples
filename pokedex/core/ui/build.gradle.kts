plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.compose)
}

android {
    namespace = "com.kfaraj.samples.pokedex.core.ui"
    compileSdk = 37
    defaultConfig {
        minSdk = 26
    }
}

kotlin {
    jvmToolchain(21)
    explicitApi()
}

dependencies {
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
