package br.com.mercury.axieinfinityapi.model.network

import br.com.mercury.axieinfinityapi.model.ItemModel
import com.google.gson.JsonObject
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AxieApi {
    @POST("graphql")
    @Headers("Content-Type: application/json")
    suspend fun getProfileInfo(
        @Body clientId: JsonObject,
    ): String
}