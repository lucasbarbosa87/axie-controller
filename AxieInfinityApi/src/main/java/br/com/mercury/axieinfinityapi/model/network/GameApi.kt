package br.com.mercury.axieinfinityapi.model.network

import br.com.mercury.axieinfinityapi.model.ItemModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GameApi {
    @GET("clients/{clientId}/items/{itemId}")
    suspend fun getItemInfo(
        @Path("clientId") clientId: String,
        @Path("itemId") itemId: String
    ): ItemModel
}