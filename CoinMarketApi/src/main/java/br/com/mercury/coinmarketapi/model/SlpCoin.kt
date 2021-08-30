package br.com.mercury.coinmarketapi.model

data class SlpCoin(
    val circulating_supply: Int,
    val cmc_rank: Int,
    val date_added: String,
    val id: Int,
    val is_active: Int,
    val is_fiat: Int,
    val last_updated: String,
    val max_supply: Any,
    val name: String,
    val num_market_pairs: Int,
    val platform: Platform,
    val quote: Quote,
    val slug: String,
    val symbol: String,
    val tags: List<String>,
    val total_supply: Int
)