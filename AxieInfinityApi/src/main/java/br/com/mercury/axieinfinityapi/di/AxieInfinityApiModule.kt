package br.com.mercury.axieinfinityapi.di

import android.content.Context
import androidx.room.Room
import br.com.mercury.axieinfinityapi.data.local.AxieDatabase
import br.com.mercury.axieinfinityapi.data.preferences.AxiePreferences
import br.com.mercury.axieinfinityapi.data.preferences.AxiePreferencesImpl
import br.com.mercury.axieinfinityapi.repository.GameApiRepository
import br.com.mercury.axieinfinityapi.repository.GameApiRepositoryImpl
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AxieInfinityApiModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AxieDatabase =
        Room.databaseBuilder(context, AxieDatabase::class.java, "axie.db").build()


    @Singleton
    @Provides
    fun provideAxiInfinityPreferences(@ApplicationContext context: Context): AxiePreferences =
        AxiePreferencesImpl(context)


    @Singleton
    @Provides
    fun provideAxieInfinityApiRepository(
        okHttpClient: OkHttpClient,
        preferences: AxiePreferences,
        database: AxieDatabase
    ): GameApiRepository =
        GameApiRepositoryImpl(okHttpClient, preferences, database)

}