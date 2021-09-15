package br.com.mercury.axieinfinityapi.repository

import android.util.Log
import br.com.mercury.axieinfinityapi.data.local.AxieDatabase
import br.com.mercury.axieinfinityapi.data.local.model.AxieAccountDb
import br.com.mercury.axieinfinityapi.data.network.AxieBriefListResponse
import br.com.mercury.axieinfinityapi.data.network.AxieListData
import br.com.mercury.axieinfinityapi.data.network.AxieProfileBriefResponse
import br.com.mercury.axieinfinityapi.data.network.EthExchangeRateResponse
import br.com.mercury.axieinfinityapi.data.preferences.AxiePreferences
import br.com.mercury.axieinfinityapi.di.axieApiProvider
import br.com.mercury.axieinfinityapi.di.gameApiProvider
import br.com.mercury.axieinfinityapi.model.ItemModel
import br.com.mercury.axieinfinityapi.model.network.AxieApi
import br.com.mercury.axieinfinityapi.model.network.AxieApiFunctions
import br.com.mercury.axieinfinityapi.model.network.GameApi
import br.com.mercury.axieinfinityapi.utils.jsonToObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient

class GameApiRepositoryImpl(
    okHttpClient: OkHttpClient,
    private val preferences: AxiePreferences,
    private val database: AxieDatabase
) : GameApiRepository {

    private val client = gameApiProvider(okHttpClient).create(GameApi::class.java)
    private val clientAxie = axieApiProvider(okHttpClient).create(AxieApi::class.java)

    private val apiFunctions = AxieApiFunctions()

    override suspend fun getProfileBrief(
        success: (response: AxieAccountDb) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        try {
            val tokenBearer = getBearerToken()
            val bodyJson = apiFunctions.profileBrief()
            val result = clientAxie.graphqlPostWithBearer(bodyJson, "Bearer $tokenBearer")
            val data = jsonToObject<AxieProfileBriefResponse>(result.data.toString())
            if (data.profile == null) {
                failure(Exception("Profile not found"))
            }
            val account = AxieAccountDb(data)
            database.accountDao().insertOrUpdateAccount(account)
            success(account)
        } catch (ex: Exception) {
            Log.d("error", ex.toString())
        }
    }

    override suspend fun getAxieBriefList(
        from: Int,
        size: Int,
        sort: String,
        auctionType: String
    ): AxieListData {
        try {
            val bodyJson =
                apiFunctions.axieBriefList(getProfile().roninAdress, from, size, sort, auctionType)

            val result = clientAxie.graphqlPost(bodyJson)
            val data = jsonToObject<AxieBriefListResponse>(result.data.toString())
            return data.axiesData
        } catch (ex: java.lang.Exception) {
            throw ex
        }
    }

    override suspend fun getProfile(): AxieAccountDb = runBlocking(Dispatchers.IO) {
        database.accountDao().getAccount()
    }

    override suspend fun getEthValue(): Double {
        try {
            val bodyJson = apiFunctions.ethExchangeRate()
            val result = clientAxie.graphqlPost(bodyJson)
            val data = jsonToObject<EthExchangeRateResponse>(result.data.toString())
            return data.exchangeRate.eth.usd
        } catch (ex: Exception) {
            Log.d("bla", ex.toString())
            return 0.0
        }
    }


    override suspend fun getAccountSlp(
        success: (response: ItemModel) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        try {
            val roninAdress = getProfile().roninAdress
            success(client.getItemInfo(roninAdress, "1"))
        } catch (ex: Exception) {
            failure(ex)
        }
    }

    private fun getBearerToken(): String {
        return preferences.getBearerToken()
    }

    override suspend fun setBearerToken(value: String) {
        preferences.setBearerToken(value)
    }

}