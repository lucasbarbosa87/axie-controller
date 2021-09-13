package br.com.mercury.axieinfinityapi.model.network

import br.com.mercury.axieinfinityapi.utils.axieBriefListQuery
import br.com.mercury.axieinfinityapi.utils.newEthExchangeRate
import br.com.mercury.axieinfinityapi.utils.profileBriefQuery
import com.google.gson.JsonObject

internal class AxieApiFunctions() {

    fun profileBrief(): JsonObject {
        val json = JsonObject()
        json.addProperty("operationName", "GetProfileBrief")
        json.addProperty("query", profileBriefQuery)
        return json
    }

    fun axieBriefList(
        owner: String,
        from: Int = 0,
        size: Int = 24,
        sort: String = "PriceAsc",
        auctionType: String = ""
    ): JsonObject {
        val json = JsonObject()
        json.addProperty("operationName", "GetAxieBriefList")
        json.addProperty("query", axieBriefListQuery)

        val jsonVariables = JsonObject().let {
            it.addProperty("owner", owner)
            it.addProperty("from", from)
            it.addProperty("size", size)
            it.addProperty("sort", sort)
            it.addProperty("auctionType", auctionType)
        }
        json.addProperty("variables", jsonVariables.toString())
        return json
    }

    fun ethExchangeRate(): JsonObject {
        val json = JsonObject()
        json.addProperty("operationName", "NewEthExchangeRate")
        json.addProperty("query", newEthExchangeRate)
        return json
    }

}