package br.com.mercury.axieinfinityapi.repository

import br.com.mercury.axieinfinityapi.data.local.model.AxieAccountDb
import br.com.mercury.axieinfinityapi.data.network.AxieListData
import br.com.mercury.axieinfinityapi.model.ItemModel

interface GameApiRepository {

    suspend fun getAccountSlp(
        success: (response: ItemModel) -> Unit,
        failure: (throwable: Throwable) -> Unit
    )

    suspend fun getProfileBrief(
        success: (response: AxieAccountDb) -> Unit,
        failure: (throwable: Throwable) -> Unit
    )

    suspend fun getAxieBriefList(
        from: Int = 0,
        size: Int = 24,
        sort: String = "PriceAsc",
        auctionType: String = ""
    ): AxieListData

    suspend fun getProfile(): AxieAccountDb

    suspend fun setBearerToken(value: String)
    suspend fun getEthValue(): Double
}