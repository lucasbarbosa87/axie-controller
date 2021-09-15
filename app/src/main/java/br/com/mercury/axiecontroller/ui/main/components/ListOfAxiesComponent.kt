package br.com.mercury.axiecontroller.ui.main.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.mercury.axiecontroller.ui.theme.AxieControllerTheme
import br.com.mercury.axieinfinityapi.data.network.AxieData

@Composable
fun ListOfAxiesComponent(axieList: SnapshotStateList<AxieData>) {

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(axieList.size) {
            Text("Item is $it", color = Color.Blue)
        }
    }
}


@Composable
@Preview
fun ListAxiesPreview() {
    AxieControllerTheme() {
        ListOfAxiesComponent(axieList = SnapshotStateList<AxieData>())
    }
}
