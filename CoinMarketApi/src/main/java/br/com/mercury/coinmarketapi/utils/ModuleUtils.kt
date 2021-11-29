package br.com.mercury.coinmarketapi.utils

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

internal fun dollarApiProvider(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://api.rates-history-service.prd.aws.ofx.com/")
        .client(okHttpClient)
        .addConverterFactory(getJsonConverter())
        .build()
}