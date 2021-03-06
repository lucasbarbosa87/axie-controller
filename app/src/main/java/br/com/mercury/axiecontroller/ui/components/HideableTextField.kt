package br.com.mercury.axiecontroller.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import br.com.mercury.axiecontroller.ui.theme.AxieControllerTheme

@Composable
fun HideableTextField(
    modifier: Modifier = Modifier,
    text: String,
    hide: Boolean,
    style: TextStyle = MaterialTheme.typography.body1,
) {

    val hiddenWord: String by remember { mutableStateOf(text.replace("[\\d|.,]".toRegex(), "*")) }
    Text(
        modifier = modifier,
        text = if (hide) {
            hiddenWord
        } else {
            text
        },
        style = style
    )
}

@Preview
@Composable
private fun HideableTextFieldVisiblePreview() {
    AxieControllerTheme {
        Surface {
            HideableTextField(
                text = "$ 10.000", hide = true
            )
        }
    }
}

@Preview
@Composable
private fun HideableTextFieldHiddenPreview() {
    AxieControllerTheme {
        Surface {
            HideableTextField(
                text = "$ 10.000", hide = false
            )
        }
    }
}