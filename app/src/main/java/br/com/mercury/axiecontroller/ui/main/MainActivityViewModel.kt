package br.com.mercury.axiecontroller.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.mercury.axieinfinityapi.repository.GameApiRepository
import br.com.mercury.axieinfinityapi.utils.objectToJson
import br.com.mercury.coinmarketapi.repository.CoinMarketRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(
    application: Application,
    private val gameApiRepository: GameApiRepository,
) :
    AndroidViewModel(application) {


//    fun testeApi() {
//        viewModelScope.launch {
//            testeAxie()
//            coinMarketRepository.getSlpValue(success = {
//                Log.i("teste", objectToJson(it))
//            }, failure = {
//                Log.i("teste", it.toString())
//            })
//        }
//    }

    suspend fun testeAxie() {
        gameApiRepository.getAccountSlp(
            "0x57b86b6953f06266845961bc3edd974902f204fc",
            success = {
                Log.i("teste", objectToJson(it))
            },
            failure = {
                Log.i("teste", it.toString())
            })

    }

}