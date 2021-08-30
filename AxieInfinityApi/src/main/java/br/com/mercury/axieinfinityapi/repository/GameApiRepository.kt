package br.com.mercury.axieinfinityapi.repository

import br.com.mercury.axieinfinityapi.model.ItemModel

interface GameApiRepository {

    suspend fun getAccountSlp(
        accountId: String,
        success: (response: ItemModel) -> Unit,
        failure: (throwable: Throwable) -> Unit
    )
}