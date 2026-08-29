plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.androidx.room3)
    alias(libs.plugins.io.insert.koin.compiler.plugin)
}

android {
    namespace = "com.kfaraj.samples.pokedex.data.pokemon"
    compileSdk = 37
    defaultConfig {
        minSdk = 28
    }
}

kotlin {
    jvmToolchain(21)
    explicitApi()
    compilerOptions {
        optIn.addAll(
            "androidx.paging.ExperimentalPagingApi"
        )
    }
}

room3 {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    api(libs.androidx.paging.runtime)
    implementation(libs.androidx.room3.paging)
    implementation(libs.androidx.room3.runtime)
    ksp(libs.androidx.room3.compiler)
    implementation(libs.io.insert.koin.android)
    implementation(libs.io.insert.koin.annotations)
    implementation(libs.io.ktor.client.content.negotiation)
    implementation(libs.io.ktor.client.core)
    implementation(libs.io.ktor.client.okhttp)
    implementation(libs.io.ktor.serialization.kotlinx.json)
    api(libs.org.jetbrains.kotlinx.coroutines.android)
    implementation(libs.org.jetbrains.kotlinx.serialization.json)
    testImplementation(libs.androidx.paging.testing)
    testImplementation(libs.io.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.org.jetbrains.kotlinx.coroutines.test)
}
