package br.com.mercury.axiecontroller.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import br.com.mercury.axiecontroller.ui.base.BaseActivity
import br.com.mercury.axiecontroller.ui.main.components.MainScreenComponent
import br.com.mercury.axiecontroller.ui.theme.AxieControllerTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AxieControllerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreenComponent(viewModel.profileValue, viewModel.axiesList)
                }
            }
        }
        setUp()
    }

    private fun setUp() {
        viewModel.error.observe(this, {
            error(it)
        })
        viewModel.getProfileValue()
        viewModel.getAxieList()
    }
}