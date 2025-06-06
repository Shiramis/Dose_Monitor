plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.dosemonitor"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.dosemonitor"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    // Chart library
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // Room dependencies
    implementation(libs.room.runtime)               // room-runtime (e.g. androidx.room:room-runtime:2.6.1)
    annotationProcessor(libs.room.compiler)         // room-compiler (e.g. androidx.room:room-compiler:2.6.1)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
