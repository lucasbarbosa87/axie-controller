val composeVersion = "1.0.0"
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    kotlin("plugin.serialization") version "1.5.20"
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "br.com.mercury.axiecontroller"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":AxieInfinityApi"))
    implementation(project(":CoinMarketApi"))
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02")

    implementation("com.google.android.gms:play-services-safetynet:17.0.1")
    implementation("com.android.volley:volley:1.2.1")
    roomDb()
    koin()
    retrofit(ConfigurationType.Implementation)
    implementation(platform("com.google.firebase:firebase-bom:28.4.0"))
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.journeyapps:zxing-android-embedded:4.2.0")


}