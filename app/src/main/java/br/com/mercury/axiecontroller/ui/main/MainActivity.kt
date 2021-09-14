package br.com.mercury.axiecontroller.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import br.com.mercury.axiecontroller.ui.base.BaseActivity
import br.com.mercury.axiecontroller.ui.main.components.AccountValueComponent
import br.com.mercury.axiecontroller.ui.main.components.ListOfAxiesComponent
import br.com.mercury.axiecontroller.ui.theme.AxieControllerTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AxieControllerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(colors.background)
                    ) {
                        AccountValueComponent(false, viewModel.profileValue)
                        Column(Modifier.fillMaxHeight()) {
                            ListOfAxiesComponent(viewModel.getAxieList)
                        }
                    }
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
        viewModel.getAxieList()
    }
}