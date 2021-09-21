fun getAppVersionCode(versionName: String): Int {
    val appVersionArray = versionName.split("\\.")

    return appVersionArray[0].toInt() * 100000 +
            appVersionArray[1].toInt() * 1000 +
            appVersionArray[2].toInt() * 10
}
