import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlinKsp)
}

android {
    namespace = "com.desserttime.core"

    defaultConfig {
        val localProperties = loadLocalProperties(rootDir)
        val baseUrl = localProperties.getProperty("BASE_URL", "")
        val kakaoUrl = localProperties.getProperty("KAKAO_API_KEY", "")
        val naverUrl = localProperties.getProperty("NAVER_API_KEY", "")
        val naverSecret = localProperties.getProperty("NAVER_API_SECRET", "")
        val googleClientId = localProperties.getProperty("GOOGLE_CLIENT_ID", "")

        buildConfigField("String", "BASE_URL", "\"$baseUrl\"")
        buildConfigField("String", "KAKAO_API_KEY", "\"$kakaoUrl\"")
        buildConfigField("String", "NAVER_API_KEY", "\"$naverUrl\"")
        buildConfigField("String", "NAVER_API_SECRET", "\"$naverSecret\"")
        buildConfigField("String", "GOOGLE_CLIENT_ID", "\"$googleClientId\"")
    }

    buildFeatures {
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":domain"))

    // viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // retrofit
    implementation(libs.bundles.retrofit)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    // logging
    implementation(libs.timber)

    // data store
    implementation(libs.datastore)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

fun loadLocalProperties(rootDir: File): Properties {
    val properties = Properties()
    val localPropertiesFile = File(rootDir, "local.properties")
    if (localPropertiesFile.exists()) {
        FileInputStream(localPropertiesFile).use { properties.load(it) }
    }
    return properties
}
