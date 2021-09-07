package br.com.mercury.axieinfinityapi.repository

import br.com.mercury.axieinfinityapi.model.ItemModel

interface GameApiRepository {

    suspend fun getAccountSlp(
        accountId: String,
        success: (response: ItemModel) -> Unit,
        failure: (throwable: Throwable) -> Unit
    )

    suspend fun getProfileBrief(tokenBearer: String)

    suspend fun getAxieBriefList(
        owner: String,
        from: Int = 0,
        size: Int = 24,
        sort: String = "PriceAsc",
        auctionType: String = ""
    )
}