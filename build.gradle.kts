buildscript {
    dependencies {
        repositories {
            google()
            mavenCentral()
        }
        val navigationVersion by extra("2.8.0-alpha08")
        val hiltVersion by extra("2.50")
        val kotlinVersion by extra("1.9.23")
        val gradleVersion by extra("8.0.2")
        classpath("com.android.tools.build:gradle:$gradleVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navigationVersion")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}