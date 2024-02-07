// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

buildscript {
    val objectboxVersion by extra("3.7.1") // For KTS build scripts

    repositories {
        mavenCentral()
        // Note: 2.9.0 and older are available on jcenter()
    }

    dependencies {
        // Android Gradle Plugin 4.1.0 or later supported.
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("io.objectbox:objectbox-gradle-plugin:$objectboxVersion")
    }
}