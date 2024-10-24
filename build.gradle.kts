// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply true
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kapt) apply false
}

allprojects {
    apply {
        plugin("org.jlleitschuh.gradle.ktlint")
        plugin("io.gitlab.arturbosch.detekt")
    }

    afterEvaluate {
        // Apply common configurations
        apply(from = "$rootDir/gradle/common.gradle")

        detekt {
            buildUponDefaultConfig = true
            config.setFrom(files("$rootDir/detekt-config.yml"))
        }
    }
}
