package br.com.mercury.axieinfinityapi.model

import com.google.gson.annotations.SerializedName

data class ItemModel(
    @SerializedName("item_id") val itemId: Int,
    @SerializedName("total") val totalItem: Int,
    @SerializedName("claimable_total") val totalClaimableItem: Int,
    @SerializedName("last_claimed_item_at") val timeStampLastClaimable: Long
)