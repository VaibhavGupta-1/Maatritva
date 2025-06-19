import org.gradle.internal.impldep.org.jsoup.helper.DataUtil.load

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.kapt")
    alias(libs.plugins.google.gms.google.services)
}


    android {
        namespace = "com.example.maatritva"
        compileSdk = 35

        defaultConfig {
            applicationId = "com.example.maatritva"
            minSdk = 24
            targetSdk = 35
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
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
            isCoreLibraryDesugaringEnabled = true
        }
        kotlinOptions {
            jvmTarget = "11"
        }
        buildFeatures {
            compose = true
        }
    }

    dependencies {

        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.activity.compose)
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.androidx.ui)
        implementation(libs.androidx.ui.graphics)
        implementation(libs.androidx.ui.tooling.preview)
        implementation(libs.androidx.material3)
        implementation(libs.firebase.auth)
        implementation(libs.androidx.credentials)
        implementation(libs.androidx.credentials.play.services.auth)
        implementation(libs.googleid)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.tooling)
        debugImplementation(libs.androidx.ui.test.manifest)

        implementation("androidx.navigation:navigation-compose:2.9.0")

        implementation("androidx.compose.material:material-icons-extended")

        // Core library desugaring for java.time API support on older Android versions
        coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

        // ThreeTenABP for java.time API support on older Android versions
        implementation("com.jakewharton.threetenabp:threetenabp:1.4.6")

        val roomVersion = "2.6.1"

        implementation("androidx.room:room-runtime:$roomVersion")
        kapt("androidx.room:room-compiler:$roomVersion")

        // Room Kotlin extensions
        implementation("androidx.room:room-ktx:$roomVersion")

        // Optional - for testing Room
        testImplementation("androidx.room:room-testing:$roomVersion")

        // LiveData
        implementation("androidx.compose.runtime:runtime-livedata")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.1")

        // Optional ViewModel support
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.1")

        // OkHttp for network requests
        implementation("com.squareup.okhttp3:okhttp:4.12.0")

        // JSON parsing
        implementation("org.json:json:20231013")

        implementation("com.google.ai.client.generativeai:generativeai:0.4.0")

        implementation("io.coil-kt:coil-compose:2.7.0")


    }