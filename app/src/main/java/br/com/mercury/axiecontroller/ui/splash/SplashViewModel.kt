package br.com.mercury.axiecontroller.ui.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.mercury.coinmarketapi.repository.CoinMarketRepository
import kotlinx.coroutines.launch

class SplashViewModel(
    application: Application, private val coinMarketRepository: CoinMarketRepository,
) : AndroidViewModel(application) {

    fun initialize(finish: () -> Unit) {
        viewModelScope.launch {
            coinMarketRepository.getAccountInfoNetwork()
//            coinMarketRepository.getSmoothLovePotionValueNetwork()
            finish()
        }
    }


}