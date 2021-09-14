package br.com.mercury.axieinfinityapi.data.network

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class AxieBriefListResponse(
    @SerializedName("axie") val axiesData: AxieListData
)

data class AxieListData(
    @SerializedName("total") val total: Int,
    @SerializedName("results") val results: List<AxieData>
)

data class AxieData(
    @SerializedName("name") val name: String,
    @SerializedName("class") val axieClass: String,
    @SerializedName("image") val image: String,
    @SerializedName("breedCount") val breedCount: String,
)
