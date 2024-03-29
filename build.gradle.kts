// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
}
buildscript {

    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.0") // Use the latest version available
        classpath ("com.google.gms:google-services:4.4.0") // Use the latest version available
        classpath ("com.squareup.picasso:picasso:2.8")

    }
}
