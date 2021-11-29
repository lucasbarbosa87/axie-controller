package br.com.mercury.axieinfinityapi.utils

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal fun gameApiProvider(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://game-api.skymavis.com/game-api/")
        .client(okHttpClient)
        .addConverterFactory(getJsonConverter())
        .build()
}

internal fun axieApiProvider(okHttpClient: OkHttpClient): Retrofit {
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
