package br.com.mercury.axiecontroller.ui.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.mercury.axiecontroller.R
import br.com.mercury.axiecontroller.ui.base.BaseViewModel
import br.com.mercury.axieinfinityapi.repository.GameApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(application: Application, private val gameApiRepository: GameApiRepository) :
    BaseViewModel(application = application) {

    val isSuccess = MutableLiveData<Boolean>()
    val message = MutableLiveData<String>()

    fun readQrCodeContent(content: String?) {
        if (content == null) {
            message.postValue(getApplication<Application>().getString(R.string.qr_code_error))
            return
        }

        viewModelScope.launch {
            gameApiRepository.setBearerToken(content)
            gameApiRepository.getProfileBrief(success = {
                isSuccess.postValue(true)
            }, failure = {
                message.postValue(it.localizedMessage)
            })
        }
    }
}