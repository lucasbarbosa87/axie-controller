package br.com.mercury.coinmarketapi.data.network

import br.com.mercury.coinmarketapi.data.network.model.DolarResponse
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.POST

internal interface DollarApi {

    @POST("rate-history/api/1")
    fun getDolarValue(@Body bodyJson: JsonObject): DolarResponse

}