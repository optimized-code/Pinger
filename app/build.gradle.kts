import java.util.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    alias(libs.plugins.com.google.dagger.hilt.android)
    kotlin("kapt")
    alias(libs.plugins.compose.compiler)

}

android {
    namespace = "io.optimizedcode.pingo"
    compileSdk = libs.versions.compileSdk.get().toInt()

    val keystorePropertiesFile = rootProject.file("keystore.properties")
    val keystoreProperties = Properties()
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))

    signingConfigs {
        create("config") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }

    defaultConfig {
        applicationId = "io.optimizedcode.pingo"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isShrinkResources = false
            isMinifyEnabled = false
            versionNameSuffix = "-dev"
            applicationIdSuffix = ".debug"
            buildConfigField("String", "ecp" , keystoreProperties["ecp"] as String)
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("config")
        }

        create("staging") {
            initWith(getByName("debug"))
            applicationIdSuffix = ".staging"
        }
    }

//    flavorDimensions.addAll(listOf("Platforms"))

//    productFlavors {
//        create("huawei") {
//            buildConfigField(
//                "String",
//                "CURRENT_PLATFORM",
//                project.properties["platform_huawei"].toString()
//            )
//            versionCode = 1
//            versionName = "1.0.0"
//            dimension = "Platforms"
//        }

//        create("google") {
//            buildConfigField(
//                "String",
//                "CURRENT_PLATFORM",
//                project.properties["platform_google`"].toString()
//            )
//            versionCode = 1
//            versionName = "1.0.0"
//            dimension = "Platforms"
//        }

//        create("free") {
//            dimension = "Sale"
//        }
//
//        create("paid") {
//            dimension = "Sale"
//        }
//    }

//    androidComponents {
//        // TODO: Implement it later
//        beforeVariants { variantBuilder ->
//            val list: List<String> = listOf("free", "paid")
//            if (variantBuilder.productFlavors.containsAll<Serializable>(list)) {
//                variantBuilder.enable = false
//            }
//        }
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
//        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

//val googleImplementation by configurations
//val huaweiImplementation by configurations

dependencies {
    implementation(project(":features:home:ui"))
    implementation(project(":core:common"))

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.navigation.compose)

    // logging
    implementation(libs.timber)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // google Dependencies /////////////////////////////////////////////////////////////////////////
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.messaging.ktx)
//    googleImplementation(libs.play.services.maps)
//    googleImplementation(libs.google.maps.services)
//    googleImplementation("com.google.maps.android:android-maps-utils:2.2.6")
//    googleImplementation("com.google.android.gms:play-services-location:21.3.0")

    // Huawei Dependencies /////////////////////////////////////////////////////////////////////////
//    huaweiImplementation("com.huawei.agconnect:agconnect-core:1.9.1.300")
//    huaweiImplementation(libs.maps)
//    huaweiImplementation("com.huawei.hms:location:4.0.3.303")
//    huaweiImplementation("com.huawei.hms:push:6.11.0.300")
//    huaweiImplementation("com.huawei.agconnect:agconnect-crash:1.9.1.300")
//    huaweiImplementation("com.huawei.hms:hianalytics:6.9.0.301")
//    huaweiImplementation("com.huawei.agconnect:agconnect-apms:1.6.2.300")
    // Huawei Dependencies /////////////////////////////////////////////////////////////////////////
}