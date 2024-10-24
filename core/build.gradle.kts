import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
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
//    compileSdk = 34
//
//    defaultConfig {
//        minSdk = 26
//
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
    kotlinOptions {
        jvmTarget = "1.8"
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
    kapt(libs.hilt.android.compiler)
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
