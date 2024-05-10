buildscript {
    dependencies {
        repositories {
            google()
            mavenCentral()
        }
        classpath("com.android.tools.build:gradle:8.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.23")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.1")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}