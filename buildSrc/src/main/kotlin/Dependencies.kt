import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.roomDb() {
    add("implementation", Libraries.roomDb)
    add("implementation", Libraries.roomGuava)
    add("implementation", Libraries.roomKtx)
    add("kapt", Libraries.roomCompiler)
    add("testImplementation", Libraries.roomTesting)
}

fun DependencyHandler.koin() {
    add("implementation", Libraries.koin)
    add("implementation", Libraries.koinViewModel)
    add("implementation", Libraries.koinTest)
}

fun DependencyHandler.lifecycleLibs() {
    add("kapt", Libraries.lifecycleCommon)
    add("implementation", Libraries.lifecycleExtensions)
    add("implementation", Libraries.lifecycleViewmodel)
}

fun DependencyHandler.navigation() {
    add("implementation", Libraries.navigationFragmentKtx)
    add("implementation", Libraries.navigationFragmentUiKtx)
}

fun DependencyHandler.appCenter() {
    add("implementation", Libraries.appCenterAnalytics)
    add("implementation", Libraries.appCenterCrashes)
}

fun DependencyHandler.retrofit(configurationType: ConfigurationType) {
    add(configurationType.value, Libraries.retrofit)
    add(configurationType.value, Libraries.retrofitConverterGson)
    add("api", "com.squareup.okhttp3:okhttp:4.9.1")
}

fun DependencyHandler.paging(configurationType: ConfigurationType) {
    add(configurationType.value, Libraries.paging2)
//    add(configurationType.value, Libraries.pagingRuntime)
//    add(configurationType.value, Libraries.pagingGuava)
}

fun DependencyHandler.stetho(configurationType: ConfigurationType) {
    add(configurationType.value, Libraries.stethoInterceptor)
    add(configurationType.value, Libraries.stethoInterceptorOkHttp)
}

fun DependencyHandler.firebase(configurationType: ConfigurationType, withAuth: Boolean = false) {
    add(configurationType.value, "com.google.firebase:firebase-core:19.0.0")
    add(configurationType.value, "com.google.firebase:firebase-messaging:22.0.0")
    add(configurationType.value, "com.google.firebase:firebase-storage:20.0.0")
    if (withAuth) {
        add(configurationType.value, Libraries.firebaseAuth)
        add(configurationType.value, Libraries.firebaseAuthSignInGoogle)
    }
//    add("implementation", "com.google.firebase:firebase-ml-vision:24.1.0")
}

fun DependencyHandler.cameraX(configurationType: ConfigurationType) {

    // The following line is optional, as the core library is included indirectly by camera-camera2
    add(configurationType.value, "androidx.camera:camera-core:${Versions.cameraxVersion}")
    add(configurationType.value, "androidx.camera:camera-camera2:${Versions.cameraxVersion}")
    // If you want to additionally use the CameraX Lifecycle library
    add(configurationType.value, "androidx.camera:camera-lifecycle:${Versions.cameraxVersion}")
}

fun DependencyHandler.compose(configurationType: ConfigurationType) {
    add(configurationType.value, Libraries.constraintLayoutCompose)
    add(configurationType.value, Libraries.materialIconsCore)
    add(configurationType.value, Libraries.materialIconsExtended)
}

fun DependencyHandler.hilt(configurationType: ConfigurationType, withCompose: Boolean = false) {
    add(configurationType.value, Libraries.hiltAndroid)
    if (withCompose) {
        add(configurationType.value, Libraries.hiltCompose)
    }
//    add(configurationType.value, Libraries.hiltAndroidGoogle)
    add("kapt", Libraries.hiltAndroidCompiler)
//    add("kapt", Libraries.hiltAndroidXCompiler)
}


enum class ConfigurationType(val value: String) {
    Implementation("implementation"), Api("api")
}