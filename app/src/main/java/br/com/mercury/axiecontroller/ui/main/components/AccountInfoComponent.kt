package br.com.mercury.axiecontroller.ui.main.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import br.com.mercury.axiecontroller.ui.theme.AxieControllerTheme

@Composable
fun AccountInfo(nome: String) {
    Surface(color = MaterialTheme.colors.background) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

            val (accountName, accountGreets) = createRefs()

            Text(text = "Boa Noite,",
                modifier = Modifier.constrainAs(accountGreets) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
            )

            Text(
                text = nome,
                modifier = Modifier.constrainAs(accountName) {
                    top.linkTo(accountGreets.bottom)
                    start.linkTo(accountGreets.start, margin = 4.dp)
                }
            )
        }
    }
}


@Composable
@Preview
fun AccountInfoPreview() {
    AxieControllerTheme {
        AccountInfo(nome = "Lucas Barbosa")
    }
}