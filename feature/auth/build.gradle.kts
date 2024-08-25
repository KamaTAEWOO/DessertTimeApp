import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.desserttime.auth"

    defaultConfig {
        val localProperties = loadLocalProperties(rootDir)
        val kakaoRedirectUrl = localProperties.getProperty("KAKAO_API_KEY", "") // 기본값을 적절한 URL로 설정

        resValue("string", "KAKAO_REDIRECT_SCHEME", "kakao$kakaoRedirectUrl")
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))
    implementation(project(":design"))
    implementation(project(":feature:controler"))
    implementation(project(":feature:like"))
    implementation(project(":feature:mypage"))
    implementation(project(":feature:review"))

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    // status color
    implementation(libs.accompanist.systemuicontroller)

    // kakao
    implementation(libs.kakao.user)

    // naver
    implementation(libs.naver.login)

    // google
    implementation(libs.google.services)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.bom)
    implementation(libs.play.services.auth)

    // Timber
    implementation(libs.timber)

    implementation(libs.activity.ktx)
    implementation(libs.androidx.lifecycle.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

fun loadLocalProperties(rootDir: File): Properties {
    val properties = Properties()
    val localPropertiesFile = File(rootDir, "local.properties")
    if (localPropertiesFile.exists()) {
        FileInputStream(localPropertiesFile).use { properties.load(it) }
    }
    return properties
}
