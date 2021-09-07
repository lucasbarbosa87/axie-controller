package br.com.mercury.axiecontroller.ui.splash

import android.app.Application
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

    fun initialize(finish: () -> Unit) {
        viewModelScope.launch {
            coinMarketRepository.getAccountInfoNetwork()
            axieRepository.setBearerToken(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NvdW50SWQiOj" +
                        "IxOTQyODMsImFjdGl2YXRlZCI6dHJ1ZSwicm9uaW5BZGRyZXNzIjoiMHg1N2I4NmI2OTUzZjA2MjY" +
                        "2ODQ1OTYxYmMzZWRkOTc0OTAyZjIwNGZjIiwiZXRoQWRkcmVzcyI6bnVsbCwiaWF0IjoxNjMwOTU4" +
                        "NzgwLCJleHAiOjE2MzE1NjM1ODAsImlzcyI6IkF4aWVJbmZpbml0eSJ9.i47MHzBl46A-J_QCttim" +
                        "u2pbGNDvBzwOYrGAQLjfctQ"
            )
            axieRepository.getProfileBrief("")
//            finish()
        }
    }


}