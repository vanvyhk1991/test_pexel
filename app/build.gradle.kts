plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id( "kotlin-kapt")
}

android {
    namespace = "com.app.testpexel"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.app.testpexel"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "BASE_URL",
            "\"https://api.pexels.com/v1/\""
        )
        buildConfigField(
            "String",
            "KEY_API",
            "\"mbJUnYZE85ATJz3KAjEDIG82Pra1Qg9YPbMcDysOjQCnkH5QFhAlD0Hu\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.rxjava)
    implementation(libs.rxandroid)
    implementation(libs.rxkotlin)
//    implementation(libs.shimmerrecyclerview)
    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.sdp.android)
    implementation(libs.koin)
    implementation(libs.retrofit)
    implementation(libs.adapter.rxjava3)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation(libs.glide)
    implementation(libs.androidx.swiperefreshlayout)
    annotationProcessor(libs.compiler)
}