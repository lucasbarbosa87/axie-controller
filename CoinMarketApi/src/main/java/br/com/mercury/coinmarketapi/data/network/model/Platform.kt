package br.com.mercury.coinmarketapi.data.network.model

data class Platform(
    val id: Int,
    val name: String,
    val slug: String,
    val symbol: String,
    val token_address: String
)