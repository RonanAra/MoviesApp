plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "com.example.moviesapp"

    buildToolsVersion = libs.versions.buildTools.get()

    defaultConfig {
        applicationId = "com.example.moviesapp"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }

    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    api(libs.bundles.commonLibs)
    api(libs.bundles.commonDebugLibs)
    testImplementation(libs.bundles.androidTest)
    testImplementation(libs.bundles.unitTest)
}