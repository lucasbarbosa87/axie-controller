package br.com.mercury.axieinfinityapi.di

import androidx.room.Room
import br.com.mercury.axieinfinityapi.data.local.AxieDatabase
import br.com.mercury.axieinfinityapi.data.preferences.AxiePreferences
import br.com.mercury.axieinfinityapi.repository.GameApiRepository
import br.com.mercury.axieinfinityapi.repository.GameApiRepositoryImpl
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val axieInfinityApi = module {
    factory { provideOkHttpClient() }
    single { AxiePreferences(androidContext()) }
    single {
        Room.databaseBuilder(androidContext(), AxieDatabase::class.java, "axie.db")
            .build()
    }
    single<GameApiRepository> { GameApiRepositoryImpl(get(), get(), get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS).build()
}

fun gameApiProvider(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://game-api.skymavis.com/game-api/")
        .client(okHttpClient)
        .addConverterFactory(getJsonConverter())
        .build()
}

fun axieApiProvider(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://graphql-gateway.axieinfinity.com/")
        .client(okHttpClient)
        .addConverterFactory(getJsonConverter())
        .build()
}

private fun getJsonConverter(): GsonConverterFactory {
    val gsonFactory = GsonBuilder().setLenient().serializeNulls().create()
    return GsonConverterFactory.create(gsonFactory)
}
