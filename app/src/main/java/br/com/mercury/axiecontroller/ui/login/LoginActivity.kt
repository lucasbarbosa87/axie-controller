package br.com.mercury.axiecontroller.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import br.com.mercury.axiecontroller.ui.base.BaseActivity
import br.com.mercury.axiecontroller.ui.main.MainActivity
import br.com.mercury.axiecontroller.ui.theme.AxieControllerTheme
import com.google.zxing.integration.android.IntentIntegrator
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private val viewModel: LoginViewModel by viewModel()

    private val qrCOdeResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val result = IntentIntegrator.parseActivityResult(
                IntentIntegrator.REQUEST_CODE,
                it.resultCode,
                it.data
            )
            viewModel.readQrCodeContent(result.contents)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AxieControllerTheme {
                Surface(color = MaterialTheme.colors.background) {
                    LoginComponent { openQrCodeActivity() }
                }
            }
        }
        setUp()
    }

    private fun setUp() {
        viewModel.message.observe(this, {
            if (it.isNotBlank()) {
                showAlert(
                    "Alerta",
                    message = it,
                    positiveButtonText = "Ok",
                    positiveButtonClickListener = { dialogInterface, _ -> dialogInterface.dismiss() })
            }
        })

        viewModel.isSuccess.observe(this, {
            if (it) {
                openMainActivity()
            }
        })
    }

    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun openQrCodeActivity() {
        val integrator = IntentIntegrator(this)
        integrator.setOrientationLocked(true)
        integrator.setBeepEnabled(false)
        integrator.setPrompt("Aponte para o QR Code")
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        qrCOdeResult.launch(integrator.createScanIntent())
    }

}