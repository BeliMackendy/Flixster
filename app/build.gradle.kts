plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.my_app.flixster"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.my_app.flixster"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.recyclerview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)

    implementation(libs.asynchttpclient)
    implementation("com.github.bumptech.glide:glide:5.0.7")
    // Glide uses this annotation processor for the generated API -- see https://bumptech.github.io/glide/doc/generatedapi.html
    annotationProcessor("com.github.bumptech.glide:compiler:5.0.7")
}