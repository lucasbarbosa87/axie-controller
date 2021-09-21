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
//                axieRepository.setBearerToken(
//                    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOjIxOTQyODMsImFjdGl2YXRlZCI6dHJ1ZSwicm9uaW5BZGRyZXNzIjoiMHg1N2I4NmI2OTUzZjA2MjY2ODQ1OTYxYmMzZWRkOTc0OTAyZjIwNGZjIiwiZXRoQWRkcmVzcyI6bnVsbCwiaWF0IjoxNjMxNzk4MzI2LCJleHAiOjE2MzI0MDMxMjYsImlzcyI6IkF4aWVJbmZpbml0eSJ9.G10SAZa-2Er1mu9jF4aTRzvRl_8ooBqyEz-zzBYT0kg"
//                )
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
