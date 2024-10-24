import com.android.build.api.dsl.Packaging

plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.deqptest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.deqptest"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        ndk {
            abiFilters.add("arm64-v8a")
        }
        externalNativeBuild {
            cmake {
                // Passes optional arguments to CMake.
                arguments(
                    "-DDEQP_TARGET=android",
                    "-DDEQP_TARGET_TOOLCHAIN=ndk-modern",
                    "-DANDROID_NDK_PATH=/Users/<USERNAME>/Library/Android/sdk/ndk/27.0.11718014/", //FIXME: add your user name here
                    "-DANDROID_ABI=arm64-v8a",
                    "-DDE_ANDROID_API=28",
                    "-DGLCTS_GTF_TARGET=gles32",
                    // This is a hack to skip the debug symbol stripping without requiring use to edit the CMakeList file
                    "-DCMAKE_STRIP=/usr/bin/true",
                )

                targets("deqp")
            }
        }
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

    packaging {
        jniLibs {
            keepDebugSymbols += "**/*deqp*.so"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.22.1"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
