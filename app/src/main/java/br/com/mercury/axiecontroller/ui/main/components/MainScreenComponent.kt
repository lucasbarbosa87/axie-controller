package br.com.mercury.axiecontroller.ui.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.mercury.axiecontroller.ui.main.AccountValueView
import br.com.mercury.axieinfinityapi.data.network.AxieData


@Composable
fun MainScreenComponent(
    profileValue: MutableState<AccountValueView>,
    axiesList: SnapshotStateList<AxieData>
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Card(
            modifier = Modifier.padding(top = 40.dp),
        ) {
            AccountInfo(nome = profileValue.value.name)
        }
        Card(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp), elevation = 4.dp,
        ) {
            AccountValueComponent(hideSensitiveData = false, profileValue = profileValue)
        }
        ListOfAxiesComponent(axieList = axiesList)
    }

}