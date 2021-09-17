package br.com.mercury.axiecontroller.utils

import java.text.NumberFormat
import java.util.*

fun currentSymble(): String {
    val locate = Locale.getDefault()
    val currency = Currency.getInstance(locate)
    return currency.symbol.replace("\\w", "")
}

fun formatValue(value: Double): String {
    val teste = NumberFormat.getCurrencyInstance(Locale.getDefault())
    teste.maximumFractionDigits = 2
    return teste.format(value).replace(currentSymble(), "")
}

fun getCurrentLocation(): Locale = Locale.getDefault()

fun getCurrentCurrency(): Currency = Currency.getInstance(getCurrentLocation())