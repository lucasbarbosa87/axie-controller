package br.com.mercury.axiecontroller.ui.splash

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.mercury.axiecontroller.ui.base.BaseViewModel
import br.com.mercury.axieinfinityapi.repository.GameApiRepository
import br.com.mercury.coinmarketapi.repository.CoinMarketRepository
import kotlinx.coroutines.launch

class SplashViewModel(
    application: Application,
    private val coinMarketRepository: CoinMarketRepository,
    private val axieRepository: GameApiRepository
) : BaseViewModel(application) {

    fun initialize(finish: (hasProfile: Boolean) -> Unit) {
        viewModelScope.launch {
            coinMarketRepository.getAccountInfoNetwork()
            axieRepository.setBearerToken(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOjIxOTQyODMsImFjdGl2YXRlZCI6dHJ1ZSwicm9uaW5BZGRyZXNzIjoiMHg1N2I4NmI2OTUzZjA2MjY2ODQ1OTYxYmMzZWRkOTc0OTAyZjIwNGZjIiwiZXRoQWRkcmVzcyI6bnVsbCwiaWF0IjoxNjMxMTQ0OTE3LCJleHAiOjE2MzE3NDk3MTcsImlzcyI6IkF4aWVJbmZpbml0eSJ9.3rDLIIn4ekMNBPgQgEDbPEkNAbeV596CsqX-wfpE8VM"
            )
            try {
                axieRepository.getProfileBrief()
                coinMarketRepository.getSmoothLovePotionValueNetwork()
                finish(true)
            } catch (ex: Exception) {
                Log.i("teste", ex.toString())
                finish(false)
            }
//            finish()
        }
    }


}