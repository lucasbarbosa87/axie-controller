package br.com.mercury.axiecontroller.ui.splash

import android.app.Application
import androidx.lifecycle.viewModelScope
import br.com.mercury.axiecontroller.ui.base.BaseViewModel
import br.com.mercury.axiecontroller.utils.getCurrentCurrency
import br.com.mercury.axieinfinityapi.repository.GameApiRepository
import br.com.mercury.coinmarketapi.repository.CoinMarketRepository
import kotlinx.coroutines.launch
import java.util.*

class SplashViewModel(
    application: Application,
    private val coinMarketRepository: CoinMarketRepository,
    private val axieRepository: GameApiRepository
) : BaseViewModel(application) {

    fun initialize(finish: (finish: Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                coinMarketRepository.getAccountInfoNetwork()
                coinMarketRepository.getSmoothLovePotionValueNetwork()
                coinMarketRepository.getCurrencyValueNetWork(getCurrentCurrency().currencyCode)
                axieRepository.getProfileBrief(success = {
                    finish(true)
                }, failure = {
                    finish(false)
                })
            } catch (ex: Exception) {
                finish(false)
            }
        }
    }


}
