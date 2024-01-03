plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.example.nacchallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nacchallenge"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName  = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(":pokemon"))
    implementation(project(":mylocations"))
    customImplementation(Dependencies.app)

}