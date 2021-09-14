package br.com.mercury.axiecontroller.ui.main

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.mercury.axiecontroller.ui.base.BaseViewModel
import br.com.mercury.axieinfinityapi.repository.GameApiRepository
import br.com.mercury.axieinfinityapi.utils.objectToJson
import br.com.mercury.coinmarketapi.repository.CoinMarketRepository
import kotlinx.coroutines.launch

class MainActivityViewModel(
    application: Application,
    private val gameApiRepository: GameApiRepository,
    private val coinApiRepository: CoinMarketRepository
) :
    BaseViewModel(application) {

    val profileValue = mutableStateOf(AccountValueView(0.0, 0, 0.0))
    val message = MutableLiveData<String>()
    val error = MutableLiveData<Throwable>()

    fun getProfileValue() {
        viewModelScope.launch {
            gameApiRepository.getEthValue().let { ethValue ->
                coinApiRepository.getSmoothLovePotionValueLocal().let { slpCoin ->
                    gameApiRepository.getAccountSlp(success = {
                        val accountValue = AccountValueView(slpCoin.price, it.totalItem, ethValue)
                        profileValue.value = accountValue
                    }, failure = {
                        error.postValue(it)
                    })
                }
            }
        }
    }

    fun teste() {
        viewModelScope.launch {
            try {
                gameApiRepository.getProfileBrief()
            } catch (ex: Exception) {
                Log.i("teste", ex.toString())
            }
        }
    }

    suspend fun testeAxie() {
        gameApiRepository.getAccountSlp(
            success = {
                Log.i("teste", objectToJson(it))
            },
            failure = {
                Log.i("teste", it.toString())
            })

    }

    fun getAxieList(){
        viewModelScope.launch {
            gameApiRepository.getAxieBriefList("")
        }
    }

}