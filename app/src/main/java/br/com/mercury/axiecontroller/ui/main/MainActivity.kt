package br.com.mercury.axiecontroller.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import br.com.mercury.axiecontroller.ui.base.BaseActivity
import br.com.mercury.axiecontroller.ui.main.components.AccountValueComponent
import br.com.mercury.axiecontroller.ui.theme.AxieControllerTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AxieControllerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AccountValueComponent(false, viewModel.profileValue)
                }
            }
        }
        viewModel.getProfileValue()
        setUp()
    }

    private fun setUp() {
        viewModel.error.observe(this, {
            error(it)
        })
    }
}