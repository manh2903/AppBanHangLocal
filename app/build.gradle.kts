plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.ndm.appbanhang"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ndm.appbanhang"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation ("androidx.multidex:multidex:2.0.1")
    implementation ("com.nex3z:notification-badge:1.0.4")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.github.javafaker:javafaker:1.0.2")
}