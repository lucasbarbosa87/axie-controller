package br.com.mercury.axiecontroller.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import br.com.mercury.axiecontroller.ui.main.MainActivity
import br.com.mercury.axiecontroller.ui.theme.AxieControllerTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AxieControllerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SplashScreen()
                }
            }
        }
        setUp()
    }

    fun setUp() {
        viewModel.initialize(finish = {
            openMainActivity()
        })
    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}