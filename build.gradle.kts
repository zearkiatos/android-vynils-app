buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.21")
    }
}

plugins {
    id("com.android.application") version "7.0.4" apply false
    id("org.jetbrains.kotlin.android") version "1.5.31" apply false
}