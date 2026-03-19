plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.serialization)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.androidx.room)
}

android {
    namespace = "com.kfaraj.samples.pokedex.data.pokemon"
    compileSdk = 36
    defaultConfig {
        minSdk = 26
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

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.io.insert.koin.android)
    implementation(libs.io.insert.koin.annotations)
    ksp(libs.io.insert.koin.ksp.compiler)
    implementation(libs.io.ktor.client.content.negotiation)
    implementation(libs.io.ktor.client.core)
    implementation(libs.io.ktor.client.okhttp)
    implementation(libs.io.ktor.serialization.kotlinx.json)
    implementation(libs.org.jetbrains.kotlinx.coroutines.android)
    implementation(libs.org.jetbrains.kotlinx.serialization.json)
    testImplementation(libs.androidx.paging.testing)
    testImplementation(libs.io.mockk)
    testImplementation(libs.junit)
    testImplementation(libs.org.jetbrains.kotlinx.coroutines.test)
}
