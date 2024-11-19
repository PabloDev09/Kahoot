plugins {
    id("com.android.application") // Android Application plugin
    id("org.jetbrains.kotlin.android") // Kotlin plugin for Android
}

android {
    namespace = "es.iesjandula.kahoot"
    compileSdk = 35

    defaultConfig {
        applicationId = "es.iesjandula.kahoot"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        // Establecer la versión de Java para la compilación de Java
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        // Establecer la versión de JVM para Kotlin
        jvmTarget = "17"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    // AndroidX dependencies
    implementation(libs.androidx.core.ktx.v1150)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.room.ktx)
}
