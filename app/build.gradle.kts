@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.google.service)
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("/Users/kamamerona/APK KEY/desserttime.jks")
            storePassword = "123456"
            keyAlias = "key0"
            keyPassword = "123456"
        }
    }
    namespace = "com.desserttime.desserttimeapp"

    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":design"))
    implementation(project(":core"))
    implementation(project(":feature:auth"))

    // logging
    implementation(libs.timber)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    // splash screen
    implementation(libs.androidx.splashscreen)

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
