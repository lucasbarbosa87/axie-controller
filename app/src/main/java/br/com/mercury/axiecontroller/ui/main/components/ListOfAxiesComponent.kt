package br.com.mercury.axiecontroller.ui.main.components

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.mercury.axiecontroller.ui.theme.AxieControllerTheme
import br.com.mercury.axieinfinityapi.data.network.AxieData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ListOfAxiesComponent(axieList: SnapshotStateList<AxieData>) {
    val list = axieList.map { it.toString() }
    println(list)
    LazyRow {
        itemsIndexed(list) { _, column ->
            Card(
                backgroundColor = Color.LightGray,
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp)
            ) {
                Text(
                    text = "sduhfuasdhfuiasdhfushaudfhasuifhusadifhusaidhfusaihf",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}



val axieData =  {
    mutableStateListOf<AxieData>(
        AxieData(name="Axie #3390806", axieClass = "Plant", image="https://storage.googleapis.com/assets.axieinfinity.com/axies/3390806/axie/axie-full-transparent.png", breedCount="3"),
        AxieData(name="Axie #3604371", axieClass="Beast", image="https://storage.googleapis.com/assets.axieinfinity.com/axies/3604371/axie/axie-full-transparent.png", breedCount="2"),
        AxieData(name="Axie #3946406", axieClass="Aquatic", image="https://storage.googleapis.com/assets.axieinfinity.com/axies/3946406/axie/axie-full-transparent.png", breedCount="3")
    )
}