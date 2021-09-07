package br.com.mercury.axiecontroller.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.mercury.axieinfinityapi.repository.GameApiRepository
import br.com.mercury.axieinfinityapi.utils.objectToJson
import kotlinx.coroutines.launch

class MainActivityViewModel(
    application: Application,
    private val gameApiRepository: GameApiRepository,
) :
    AndroidViewModel(application) {

    fun teste() {
        viewModelScope.launch {
            gameApiRepository.getProfileBrief(
                "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJh" +
                        "Y2NvdW50SWQiOjIxOTQyODMsImFjdGl2YXRlZCI6dHJ1ZSwicm9uaW5BZGRyZXNzIjoiMHg1N2I4" +
                        "NmI2OTUzZjA2MjY2ODQ1OTYxYmMzZWRkOTc0OTAyZjIwNGZjIiwiZXRoQWRkcmVzcyI6bnVsbCwi" +
                        "aWF0IjoxNjMwOTU4NzgwLCJleHAiOjE2MzE1NjM1ODAsImlzcyI6IkF4aWVJbmZpbml0eSJ9.i47" +
                        "MHzBl46A-J_QCttimu2pbGNDvBzwOYrGAQLjfctQ"
            )
        }
    }


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