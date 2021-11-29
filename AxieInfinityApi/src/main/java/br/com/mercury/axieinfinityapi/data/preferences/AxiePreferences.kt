package br.com.mercury.axieinfinityapi.data.preferences

interface AxiePreferences {

    fun getBearerToken(): String

    fun setBearerToken(value: String)
}