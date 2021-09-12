package br.com.mercury.axiecontroller.ui.base

import android.content.DialogInterface
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

open class BaseActivity : AppCompatActivity() {


    fun showAlert(
        @StringRes title: Int,
        @StringRes message: Int,
        @StringRes positiveButtonText: Int,
        positiveButtonClickListener: DialogInterface.OnClickListener
    ) {
        showAlert(
            title = getString(title),
            message = getString(message),
            positiveButtonText = getString(positiveButtonText),
            positiveButtonClickListener = positiveButtonClickListener
        )
    }

    fun showAlert(
        title: String,
        message: String,
        positiveButtonText: String,
        positiveButtonClickListener: DialogInterface.OnClickListener
    ) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText, positiveButtonClickListener)
            .show()
    }


}