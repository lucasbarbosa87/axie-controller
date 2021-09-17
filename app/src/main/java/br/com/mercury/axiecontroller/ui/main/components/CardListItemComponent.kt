package br.com.mercury.axiecontroller.ui.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.Dimension.Companion.value
import br.com.mercury.axieinfinityapi.data.network.AxieData
import coil.compose.rememberImagePainter
import com.google.android.gms.common.config.GservicesValue.value

@Composable
fun CardListItemComponent(axie: AxieData) {
    Card(
        backgroundColor = Color.LightGray,
        modifier = Modifier
            .fillMaxWidth(fraction = 0.5f)
            .fillMaxHeight(fraction = 0.5f)
            .padding(4.dp)
    ) {
        Text(
            text = axie.name,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        Image(
            painter = rememberImagePainter(axie.image),
            contentDescription = "",
            modifier = Modifier.size(128.dp)
        )
    }
}

@Preview
@Composable
fun Preview() {
    CardListItemComponent(AxieDummyData())
}

fun AxieDummyData() =
     AxieData(
            name = "Axie #3390806",
            axieClass = "Plant",
            image = "https://storage.googleapis.com/assets.axieinfinity.com/axies/3390806/axie/axie-full-transparent.png",
            breedCount = "3"
        )
