package br.com.mercury.axiecontroller.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import br.com.mercury.axiecontroller.R

@Composable
fun LoginComponent(onLoginCLick: (() -> Unit)) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (logo, text, button) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.logo_controller),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(
                    logo
                ) {
                    top.linkTo(parent.top, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .size(96.dp, 96.dp)
        )
        Text(
            text = "Visite o Marketplace e\n scan o seu QR code",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(logo.bottom, margin = 64.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            textAlign = TextAlign.Center
        )
        Button(onClick = {
            onLoginCLick()
        },
            modifier = Modifier.constrainAs(button) {
                top.linkTo(text.bottom, margin = 96.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Text(text = "Scan QR code")
        }
    }

}


@Preview
@Composable
fun LoginomponentPreview() {
    LoginComponent(onLoginCLick = {

    })

}