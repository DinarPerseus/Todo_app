plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.parcelize")

}

android {
    namespace = "com.example.myapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myapplication"
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
//        compose = true
    }
}

dependencies {


    // Preferences DataStore
    implementation(libs.androidx.datastore.preferences)

    //Gson
    implementation(libs.gson)

    implementation (libs.retrofit.v290)
    implementation (libs.retrofit2.converter.gson)
    implementation (libs.kotlinx.coroutines.android)

    //Glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)

    //retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.retrofit.v290)
    implementation (libs.retrofit2.converter.gson)
    implementation (libs.okhttp)


    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v262)
    implementation(libs.androidx.lifecycle.livedata.ktx.v262)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.core.ktx.v1120)

    //CoroutinesCallAdapter
    implementation (libs.jakewharton.retrofit2.kotlin.coroutines.adapter)



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}


