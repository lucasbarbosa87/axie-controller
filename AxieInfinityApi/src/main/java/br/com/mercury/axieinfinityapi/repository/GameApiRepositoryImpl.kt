package br.com.mercury.axieinfinityapi.repository

import android.util.Log
import br.com.mercury.axieinfinityapi.data.local.AxieDatabase
import br.com.mercury.axieinfinityapi.data.local.model.AccountDb
import br.com.mercury.axieinfinityapi.data.network.AxieBriefListResponse
import br.com.mercury.axieinfinityapi.data.network.AxieProfileBriefResponse
import br.com.mercury.axieinfinityapi.data.preferences.AxiePreferences
import br.com.mercury.axieinfinityapi.di.axieApiProvider
import br.com.mercury.axieinfinityapi.di.gameApiProvider
import br.com.mercury.axieinfinityapi.model.ItemModel
import br.com.mercury.axieinfinityapi.model.network.AxieApi
import br.com.mercury.axieinfinityapi.model.network.AxieApiFunctions
import br.com.mercury.axieinfinityapi.model.network.GameApi
import br.com.mercury.axieinfinityapi.utils.jsonToObject
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.json.JSONObject
import kotlin.math.log

class GameApiRepositoryImpl(
    okHttpClient: OkHttpClient,
    private val preferences: AxiePreferences,
    private val database: AxieDatabase
) : GameApiRepository {

    private val client = gameApiProvider(okHttpClient).create(GameApi::class.java)
    private val clientAxie = axieApiProvider(okHttpClient).create(AxieApi::class.java)

    private val apiFunctions = AxieApiFunctions()

    override suspend fun getProfileBrief(
    ): Boolean {
        return try {
            val tokenBearer = getBearerToken()
            val bodyJson = apiFunctions.profileBrief()
            val result = clientAxie.graphqlPostWithBearer(bodyJson, "Bearer $tokenBearer")
            val data = jsonToObject<AxieProfileBriefResponse>(result.data.toString())
            if(data.profile == null){
                throw Exception("Profile not found")
            }
            val account = AccountDb(data)
            database.accountDao().insertOrUpdateAccount(account)
            true
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getAxieBriefList(
        owner: String,
        from: Int,
        size: Int,
        sort: String,
        auctionType: String
    ) {
        try {
            val bodyJson = apiFunctions.axieBriefList(owner, from, size, sort, auctionType)
            val result = clientAxie.graphqlPost(bodyJson)
            val data = jsonToObject<AxieBriefListResponse>(result.data.toString())
        } catch (ex: java.lang.Exception) {
            Log.d("bla", ex.toString())
        }
    }


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

    private fun getBearerToken(): String {
        return preferences.getBearerToken()
    }

    override suspend fun setBearerToken(value: String) {
        preferences.setBearerToken(value)
    }

}