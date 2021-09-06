package br.com.mercury.axiecontroller

import android.app.Application
import br.com.mercury.axiecontroller.di.appModule
import br.com.mercury.axieinfinityapi.di.axieInfinityApi
import br.com.mercury.axieinfinityapi.utils.initializeStetho
import br.com.mercury.coinmarketapi.di.coinMarketApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(arrayListOf(axieInfinityApi, coinMarketApi, appModule))
        }

        if (BuildConfig.DEBUG) {
            initializeStetho(this)
        }

    }

}