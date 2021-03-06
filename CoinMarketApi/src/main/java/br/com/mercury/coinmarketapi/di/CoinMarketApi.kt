package br.com.mercury.coinmarketapi.di

import androidx.room.Room
import br.com.mercury.coinmarketapi.data.local.CoinMarketDatabase
import br.com.mercury.coinmarketapi.data.network.CoinMarketApi
import br.com.mercury.coinmarketapi.data.preferences.CoinMarketPreferences
import br.com.mercury.coinmarketapi.data.preferences.CoinMarketPreferencesImpl
import br.com.mercury.coinmarketapi.repository.CoinMarketRepository
import br.com.mercury.coinmarketapi.repository.CoinMarketRepositoryImpl
import br.com.mercury.coinmarketapi.utils.dbName
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coinMarketApi = module {
    factory { provideRetrofit(get()) }
    single { provideCoinMarketApi(get()) }
    single {
        Room.databaseBuilder(androidContext(), CoinMarketDatabase::class.java, dbName)
            .build()
    }
    factory<CoinMarketRepository> { CoinMarketRepositoryImpl(get(), get(), get(), get()) }
    single<CoinMarketPreferences> { CoinMarketPreferencesImpl(androidContext()) }
}

internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
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

internal fun provideCoinMarketApi(retrofit: Retrofit): CoinMarketApi =
    retrofit.create(CoinMarketApi::class.java)

internal fun dollarApiProvider(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.rates-history-service.prd.aws.ofx.com/")
        .client(okHttpClient)
        .addConverterFactory(getJsonConverter())
        .build()
}