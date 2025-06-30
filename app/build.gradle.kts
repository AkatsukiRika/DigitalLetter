plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.drm.to.ssy.digitalletter"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.drm.to.ssy.digitalletter"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
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
    implementation(libs.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.media3.exoplayer)
    implementation(libs.media3.ui)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

tasks.register("assembleAndInstallDebug") {
    group = "custom"
    description = "Builds debug APK, installs it, and launches a specified Activity"

    dependsOn("assembleDebug")

    doLast {
        // APK 路径
        val apkPath = "${buildDir}/outputs/apk/debug/app-debug.apk"

        // 读取 SDK 路径并构建 adb 路径
        val sdkDir = android.sdkDirectory
        val adbPath = File(sdkDir, "platform-tools/adb").absolutePath

        // 包名和启动 Activity
        val packageName = "com.drm.to.ssy.digitalletter"
        val activityName = "com.drm.to.ssy.digitalletter.ui.resume.MemoryOnResumeActivity"
        val componentName = "$packageName/$activityName"

        // 检查 adb 是否存在
        if (!File(adbPath).exists()) {
            throw GradleException("adb not found at: $adbPath")
        }

        println("Installing APK from: $apkPath")
        exec {
            commandLine(adbPath, "install", "-r", apkPath)
        }

        println("Launching activity: $componentName")
        exec {
            commandLine(adbPath, "shell", "am", "start", "-n", componentName)
        }
    }
}
