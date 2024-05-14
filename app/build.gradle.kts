plugins {
    id("com.android.application") // Apply the Android application plugin
    id("kotlin-android") // Apply the Kotlin Android plugin
    id("kotlin-kapt") // Apply the Kotlin Kapt plugin
}

android {
    namespace = "com.example.todolist"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.todolist"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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
        viewBinding =true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // add below dependency for using room.

    implementation ("androidx.room:room-runtime:2.2.5")

    kapt ("androidx.room:room-compiler:2.2.5")

// add below dependency for using lifecycle extensions for room.

    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")

    kapt ("androidx.lifecycle:lifecycle-compiler:2.2.0")

    implementation ("com.google.code.gson:gson:2.10.1")
}
