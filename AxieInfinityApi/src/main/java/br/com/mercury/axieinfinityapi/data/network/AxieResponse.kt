package br.com.mercury.axieinfinityapi.data.network

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class AxieResponse(
    @SerializedName("data") val data: JsonObject
)
