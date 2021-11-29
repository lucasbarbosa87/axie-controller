package br.com.mercury.axiecontroller.ui.main

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.mercury.axiecontroller.ui.base.BaseViewModel
import br.com.mercury.axieinfinityapi.data.network.AxieData
import br.com.mercury.axieinfinityapi.repository.GameApiRepository
import br.com.mercury.coinmarketapi.repository.CoinMarketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    application: Application,
    private val gameApiRepository: GameApiRepository,
    private val coinApiRepository: CoinMarketRepository
) :
    BaseViewModel(application) {

    val profileValue = mutableStateOf(AccountValueView(0.0, 0, 0.0, "Lucas"))
    val axiesList = mutableStateListOf<AxieData>()
    val message = MutableLiveData<String>()
    val error = MutableLiveData<Throwable>()

    fun getProfileValue() {
        viewModelScope.launch {
            try {
                val dollar = runBlocking { coinApiRepository.getDollarValue() }
                gameApiRepository.getProfile().let { profile ->
                    gameApiRepository.getEthValue().let { ethValue ->
                        coinApiRepository.getSmoothLovePotionValueLocal().let { slpCoin ->
                            gameApiRepository.getAccountSlp(success = {
                                val accountValue =
                                    AccountValueView(
                                        slpCoin.price,
                                        it.totalItem,
                                        ethValue * dollar,
                                        profile.name
                                    )
                                profileValue.value = accountValue
                            }, failure = {
                                error.postValue(it)
                            })
                        }
                    }
                }
            } catch (ex: Exception) {
                Log.d("error", ex.toString())
            }
        }
    }

    fun getAxieList() {
        viewModelScope.launch {
            axiesList.addAll(gameApiRepository.getAxieBriefList().results)
        }
    }

}