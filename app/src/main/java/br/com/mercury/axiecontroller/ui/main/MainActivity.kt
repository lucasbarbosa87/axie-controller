package br.com.mercury.axiecontroller.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import br.com.mercury.axiecontroller.databinding.ActivityMainBinding
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.safetynet.SafetyNet
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    private fun setUp() {
        binding.test.setOnClickListener {
            viewModel.testeApi()
            onClick(it)
        }
    }

    fun onClick(view: View) {
        SafetyNet.getClient(this).verifyWithRecaptcha("6LddMjIcAAAAAEyedbz86U54q49BihYTNVM-tB4B")
            .addOnSuccessListener { response ->
                val userResponseToken = response.tokenResult
                if (response.tokenResult?.isNotEmpty() == true) {
                    Toast.makeText(this, "token: ${response.tokenResult}", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { e ->
                if (e is ApiException) {
                    // An error occurred when communicating with the
                    // reCAPTCHA service. Refer to the status code to
                    // handle the error appropriately.
                    Log.d("d", "Error: ${CommonStatusCodes.getStatusCodeString(e.statusCode)}")
                } else {
                    // A different, unknown type of error occurred.
                    Log.d("d", "Error: ${e.message}")
                }
            }
    }
}