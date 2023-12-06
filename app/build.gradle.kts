plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // Kapt annotation processor
    id("org.jetbrains.kotlin.kapt")
    // Ksp
    id("com.google.devtools.ksp")
    // Hilt
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.sterdevs.mybills"
    compileSdk = 34

    signingConfigs {
        getByName("debug") {
            storePassword = rootProject.extra["keyStorePassword"] as String
            keyAlias = rootProject.extra["keyAlias"] as String
            keyPassword = rootProject.extra["keyDebugPassword"] as String
            storeFile = file(rootProject.extra["debugKeyFile"] as String)
        }
        create("release") {
        }
    }

    defaultConfig {
        applicationId = "com.sterdevs.mybills"
        minSdk = 26
        //noinspection OldTargetApi
        targetSdk = 33
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
        }
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
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Hilt for dependency
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.49")

//    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.annotation:annotation:1.7.0")

    testImplementation("junit:junit:4.12")

    //Room Library
    implementation ("androidx.room:room-runtime:2.6.0")
    implementation ("androidx.room:room-ktx:2.6.0")
    ksp("androidx.room:room-compiler:2.6.0")

    //kotlin coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Retrofit with Moshi Converter :
    // https://developer.android.com/codelabs/basic-android-kotlin-training-getting-data-internet#7
    // https://github.com/square/moshi
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // LiveData and ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.2")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    // SplashScreen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Navigation component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}