package br.com.mercury.axieinfinityapi.model.network

import br.com.mercury.axieinfinityapi.data.network.AxieResponse
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

internal interface AxieApi {
    @POST("graphql")
    @Headers("Content-Type: application/json")
    suspend fun graphqlPost(
        @Body bodyJson: JsonObject,
    ): AxieResponse

    @POST("graphql")
    @Headers("Content-Type: application/json")
    suspend fun graphqlPostWithBearer(
        @Body clientId: JsonObject,
        @Header("Authorization") authString: String
    ): AxieResponse
}