plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.kapt)
    id("kotlin-parcelize")
}

android {
    namespace = "com.moha.notetest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.moha.notetest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        dataBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {

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
    implementation(libs.kotlin.stdlib)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.material.design)
    implementation(libs.fragmentKtx)
    // AndroidX Test - Core library
    testImplementation(libs.testCore)
    // Use mockito-inline for final method support
    testImplementation(libs.mockito)
    // Mockito for mocking
    testImplementation(libs.mockitoCore)
    testImplementation(libs.mockitoKotlin)
    testImplementation(libs.mockk)
    // Coroutine testing
    testImplementation(libs.coroutinesTest)
    // Required for LiveData testing
    testImplementation(libs.coreTesting)
    // Coroutines Test dependencies
    testImplementation(libs.coroutinesTest)
    testImplementation(libs.robolectric)
    // UI Test
    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.junitKtx)

    // Room
    implementation(libs.roomRuntime)
    kapt(libs.roomCompiler)
    implementation(libs.roomKtx)

    // coroutine
    implementation(libs.coroutineAndroid)
    implementation(libs.coroutineCore)
}