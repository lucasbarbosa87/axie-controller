package br.com.mercury.coinmarketapi.di

import br.com.mercury.coinmarketapi.network.ApiInterceptor
import br.com.mercury.coinmarketapi.network.CoinMarketApi
import br.com.mercury.coinmarketapi.repository.CoinMarketRepository
import br.com.mercury.coinmarketapi.repository.CoinMarketRepositoryImpl
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val coinMarketApi = module {
    factory { ApiInterceptor() }
    factory { provideRetrofit(get()) }
    single { provideCoinMarketApi(get()) }
    factory<CoinMarketRepository> { CoinMarketRepositoryImpl(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://pro-api.coinmarketcap.com/v1/")
        .client(okHttpClient)
        .addConverterFactory(getJsonConverter())
        .build()
}

private fun getJsonConverter(): GsonConverterFactory {
    val gsonFactory = GsonBuilder().setLenient().serializeNulls().create()
    return GsonConverterFactory.create(gsonFactory)
}

fun provideCoinMarketApi(retrofit: Retrofit): CoinMarketApi =
    retrofit.create(CoinMarketApi::class.java)