package br.com.mercury.coinmarketapi.di

import android.content.Context
import androidx.room.Room
import br.com.mercury.coinmarketapi.data.local.CoinMarketDatabase
import br.com.mercury.coinmarketapi.data.network.CoinMarketApi
import br.com.mercury.coinmarketapi.data.preferences.CoinMarketPreferences
import br.com.mercury.coinmarketapi.data.preferences.CoinMarketPreferencesImpl
import br.com.mercury.coinmarketapi.repository.CoinMarketRepository
import br.com.mercury.coinmarketapi.repository.CoinMarketRepositoryImpl
import br.com.mercury.coinmarketapi.utils.dbName
import br.com.mercury.coinmarketapi.utils.provideRetrofit
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinMarkekApiModule {

    @Singleton
    @Provides
    fun provideOkHttpClientCoinMarket(): OkHttpClient = OkHttpClient().newBuilder()
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS).build()

    @Singleton
    @Provides
    fun prodivdesRetrofit(okHttpClient: OkHttpClient) = provideRetrofit(okHttpClient)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CoinMarketDatabase {
        return Room.databaseBuilder(context, CoinMarketDatabase::class.java, dbName)
            .build()
    }

    @Singleton
    @Provides
    fun provideCoinMarketPreferencees(@ApplicationContext context: Context): CoinMarketPreferences =
        CoinMarketPreferencesImpl(context)

    @Singleton
    @Provides
    fun provideCoinMarketApi(retrofit: Retrofit): CoinMarketApi =
        retrofit.create(CoinMarketApi::class.java)

    @Provides
    @Singleton
    fun provideCoinMarketRepository(
        coinMarketApi: CoinMarketApi,
        coinMarketDatabase: CoinMarketDatabase,
        coinMarketPreferences: CoinMarketPreferences,
        okHttpClient: OkHttpClient
    ): CoinMarketRepository =
        CoinMarketRepositoryImpl(
            coinMarketApi, coinMarketDatabase, coinMarketPreferences, okHttpClient
        )
}