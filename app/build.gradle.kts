
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.parcelize")
    //id("com.android.application")
    //id("org.jetbrains.kotlin.android")
    //id("com.google.gms.google-services")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

//dependencies {
//// Preferences DataStore
//    implementation(libs.androidx.datastore.preferences)
//
//    //Gson
//    implementation(libs.gson)
//
//    implementation (libs.retrofit.v290)
//    implementation (libs.retrofit2.converter.gson)
//    implementation (libs.kotlinx.coroutines.android)
//
//    //Glide
//    implementation (libs.glide)
//    implementation(libs.androidx.activity)
//    implementation(libs.androidx.navigation.compose)
//    implementation(libs.androidx.navigation.compose.jvmstubs)
//    annotationProcessor (libs.compiler)
//
//    //retrofit
//    implementation (libs.retrofit)
//    implementation (libs.converter.gson)
//    implementation (libs.retrofit.v290)
//    implementation (libs.retrofit2.converter.gson)
//    implementation (libs.okhttp)
//
//
//    // ViewModel
//    implementation(libs.androidx.lifecycle.viewmodel.ktx.v262)
//    implementation(libs.androidx.lifecycle.livedata.ktx.v262)
//    implementation(libs.androidx.lifecycle.runtime.ktx)
//    implementation(libs.androidx.core.ktx.v1120)
//
//    //CoroutinesCallAdapter
//    implementation (libs.jakewharton.retrofit2.kotlin.coroutines.adapter)
//
//    //Compose
//    implementation (libs.androidx.ui)
//    implementation (libs.androidx.material)
//    implementation (libs.androidx.runtime)
//    implementation (libs.androidx.activity.compose)
//    implementation (platform(libs.androidx.compose.bom))
//    implementation (libs.androidx.ui.graphics)
//    implementation (libs.androidx.ui.tooling.preview)
//    implementation("androidx.compose.ui:ui:1.7.8")
//    implementation("androidx.compose.material:material:1.7.8")
//    implementation("androidx.compose.ui:ui-tooling-preview:1.7.8")
//    implementation("androidx.compose.material:material-icons-core:1.7.8")
//    implementation("androidx.compose.material:material-icons-extended:1.7.8")
//
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//    implementation(libs.androidx.constraintlayout)
//    implementation(libs.androidx.lifecycle.livedata.ktx)
//    implementation(libs.androidx.lifecycle.viewmodel.ktx)
//    implementation(libs.androidx.navigation.fragment.ktx)
//    implementation(libs.androidx.navigation.ui.ktx)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
//    debugImplementation(libs.androidx.ui.tooling)
//
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx.v270)
//    implementation(libs.androidx.activity.compose.v182)
//    implementation(platform(libs.androidx.compose.bom.v20230800))
//    implementation(libs.androidx.compose.ui.ui)
//    implementation(libs.ui.graphics)
//    implementation(libs.androidx.compose.ui.ui.tooling.preview)
//    implementation(libs.androidx.material3)
//    implementation(libs.androidx.navigation.compose.v290alpha08) // Correct dependency
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.androidx.junit.v115)
//    androidTestImplementation(libs.androidx.espresso.core.v351)
//    androidTestImplementation(platform(libs.androidx.compose.bom))
//    androidTestImplementation(libs.androidx.ui.test.junit4)
//    debugImplementation(libs.ui.tooling)
//    debugImplementation(libs.androidx.ui.test.manifest)
//
//}


dependencies {
    // Preferences DataStore
    implementation(libs.androidx.datastore.preferences)

    // Gson
    implementation(libs.gson)

    // Retrofit & OkHttp
    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.jakewharton.retrofit2.kotlin.coroutines.adapter)

    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Glide (for image loading)
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    // Jetpack Navigation
    implementation(libs.androidx.navigation.compose.v290alpha08) // Use latest one
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation (libs.androidx.ui.tooling.preview)
    implementation("androidx.compose.ui:ui:1.7.8")
    implementation("androidx.compose.material:material:1.7.8")
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.8")
    implementation("androidx.compose.material:material-icons-core:1.7.8")
    implementation("androidx.compose.material:material-icons-extended:1.7.8")


    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom.v20230800)) // Use BOM for consistency
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.activity.compose.v182)

    // ViewModel & LiveData (Jetpack Lifecycle)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Android Core & UI
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // Testing Dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)
    androidTestImplementation(platform(libs.androidx.compose.bom.v20230800))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debugging tools
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}


