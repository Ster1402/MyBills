buildscript {
    repositories {
        google()
    }

    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
    }
    
    // Signing config keys
    val keyStorePassword by extra("enspd-mybills-debug1402")
    val keyAlias by extra("debugKey")
    val keyDebugPassword by extra("enspd-mybills-debug14")
    val debugKeyFile by extra("C:\\Users\\JORDY\\AndroidStudioProjects\\MyBills\\app\\keys\\debug\\debugSigningKey.jks")
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    //id("com.android.application") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    // Ksp
    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
    // Hilt
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
}

