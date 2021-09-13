package br.com.mercury.axiecontroller.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import br.com.mercury.axiecontroller.ui.base.BaseActivity
import br.com.mercury.axiecontroller.ui.login.LoginActivity
import br.com.mercury.axiecontroller.ui.main.MainActivity
import br.com.mercury.axiecontroller.ui.theme.AxieControllerTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AxieControllerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SplashComponent()
                }
            }
        }
        setUp()
    }

    private fun setUp() {
        viewModel.initialize(finish = {
            if (it) {
                openMainActivity()
            } else {
                openLoginActivity()
            }
        })
    }

    private fun openLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}