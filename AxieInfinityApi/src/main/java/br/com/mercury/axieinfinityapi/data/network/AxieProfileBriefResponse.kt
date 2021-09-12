package br.com.mercury.axieinfinityapi.data.network

import com.google.gson.annotations.SerializedName

data class AxieProfileBriefResponse(
    @SerializedName("profile") val profile: AxieProfile?
)

data class AxieProfile(
    @SerializedName("accountId") val accountId: String,
    @SerializedName("addresses") val adress: AxieProfileAdress,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String
)

data class AxieProfileAdress(
    @SerializedName("ronin") val ronin: String
)