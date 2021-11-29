package br.com.mercury.axiecontroller

import android.app.Application
import br.com.mercury.axieinfinityapi.utils.initializeStetho
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            initializeStetho(this)
        }

    }

}