// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false
    kotlin("plugin.serialization") version "1.5.31" apply false
    id("com.google.dagger.hilt.android") version "2.51" apply false
}