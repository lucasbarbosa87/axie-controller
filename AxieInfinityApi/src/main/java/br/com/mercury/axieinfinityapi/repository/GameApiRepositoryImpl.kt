package br.com.mercury.axieinfinityapi.repository

import br.com.mercury.axieinfinityapi.di.gameApiProvider
import br.com.mercury.axieinfinityapi.model.ItemModel
import br.com.mercury.axieinfinityapi.model.network.GameApi
import okhttp3.OkHttpClient

class GameApiRepositoryImpl(okHttpClient: OkHttpClient) : GameApiRepository {

    private val client = gameApiProvider(okHttpClient).create(GameApi::class.java)

    override suspend fun getAccountSlp(
        accountId: String,
        success: (response: ItemModel) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        try {
            success(client.getItemInfo(accountId, "1"))
        } catch (ex: Exception) {
            failure(ex)
        }
    }
}