package br.com.mercury.axiecontroller.ui.main

class AccountValueView(val slpPrice: Double, val totalSlp: Int, val ethValue: Double) {

    fun accountValue(): Double {
        return (slpPrice * totalSlp) * ethValue
    }

}