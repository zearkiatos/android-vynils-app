
plugins {
    id("com.google.devtools.ksp") version "1.5.30-1.0.0"
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
}

android {
    namespace = "com.androidvynils.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.androidvynils.app"
        minSdk = 24
        targetSdk = 33
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
            buildConfigField("String", "BASE_API_URL", "${project.findProperty("VYNILS_BASE_API_URL_RELEASE")}")
        }
        debug {
            buildConfigField("String", "BASE_API_URL", "${project.findProperty("VYNILS_BASE_API_URL_DEBUG")}")
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
        dataBinding = true
        buildConfig = true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("com.android.volley:volley:1.2.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation( "com.github.bumptech.glide:glide:4.8.0")
    implementation("androidx.room:room-ktx:2.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("androidx.room:room-runtime:2.3.0")
    implementation("androidx.room:room-ktx:2.3.0")
    ksp("androidx.room:room-compiler:2.3.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.8.0")
    annotationProcessor("android.arch.persistence.room:compiler:1.1.1")
    ksp("com.google.devtools.ksp:symbol-processor:1.5.31")
    /* kapt("androidx.room:room-compiler:2.3.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.8.0") */
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.room:room-testing:2.3.0")
}