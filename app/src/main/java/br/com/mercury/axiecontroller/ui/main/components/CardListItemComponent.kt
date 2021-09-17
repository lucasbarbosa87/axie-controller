package br.com.mercury.axiecontroller.ui.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension.Companion.value
import br.com.mercury.axiecontroller.R
import br.com.mercury.axiecontroller.ui.theme.ColorBreedText
import br.com.mercury.axiecontroller.ui.theme.ColorCardBackground
import br.com.mercury.axiecontroller.utils.AxieType
import br.com.mercury.axieinfinityapi.data.network.AxieData
import coil.compose.rememberImagePainter
import com.google.android.gms.common.config.GservicesValue.value

@Composable
fun CardListItemComponent(axie: AxieData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.6f)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(
                top = 4.dp,
                end = 8.dp,
                bottom = 4.dp,
                start = 8.dp
            ),
        backgroundColor = ColorCardBackground,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = " " + axie.name.split(" ").filter { it != "Axie" }.joinToString(" ") + " ",
                textAlign = TextAlign.Left,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(AxieType.builder(axie.axieClass).color)
            )
            Row(modifier = Modifier.padding(start = 16.dp, top = 8.dp)) {
                AxieIconImage(AxieType.builder(axie.axieClass))
                Text(
                    text = axie.name,
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 4.dp, bottom = 0.dp)
                )

            }
            Text(
                text = "Breed count: ${axie.breedCount}",
                style = MaterialTheme.typography.subtitle1,
                color = ColorBreedText,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
            Image(
                painter = rememberImagePainter(axie.image),
                contentDescription = "",
                modifier = Modifier.size(300.dp)

            )
        }

    }
}

@Preview
@Composable
fun Preview() {
    CardListItemComponent(dummyData)
}

val dummyData =
    AxieData(
        name = "Axie #3390806",
        axieClass = "Mech",
        image = "https://storage.googleapis.com/assets.axieinfinity.com/axies/3390806/axie/axie-full-transparent.png",
        breedCount = "3"
    )

@Composable
fun AxieIconImage(type: AxieType) {
    Column(modifier = Modifier.padding(top = 4.dp)) {
        Image(
            painter = painterResource(type.icon),  // material icon
            contentDescription = "",
            colorFilter = ColorFilter.tint(type.color),
            contentScale = ContentScale.Fit,

            )
    }

}


@Preview
@Composable
fun IconImagePreview() {
    MaterialTheme {
        AxieIconImage(AxieType.builder("Aquatic"))
    }
}