package br.com.mercury.coinmarketapi.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.headers().newBuilder().let {
            it.add("X-CMC_PRO_API_KEY", "1da4fe19-dee3-49e5-b5ac-5a0913b68fce")
            it.add("Accepts", "application/json")
        }.build()
        return chain.proceed(request)
    }
}