package br.com.mercury.axiecontroller.ui.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import br.com.mercury.axiecontroller.R
import br.com.mercury.axiecontroller.ui.components.HideableTextField
import br.com.mercury.axiecontroller.ui.main.AccountValueView
import br.com.mercury.axiecontroller.ui.theme.AxieControllerTheme
import br.com.mercury.axiecontroller.utils.currentSymble
import br.com.mercury.axiecontroller.utils.formatValue

@Composable
fun AccountValueComponent(
    hideSensitiveData: Boolean,
    profileValue: MutableState<AccountValueView>
) {
    Surface(color = MaterialTheme.colors.background) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // balanceConstrainsRefs
            val (moneyRefSymbol, moneyValue, balanceText) = createRefs()

            // incomeConstrainsRefs
            val (incomeIcon, moneyValueIncome, moneySymbolIncome, incomeText) = createRefs()

            // ExpenseConstrainsRefs
            val (slpIcon, moneyValueExpense, moneySymbolExpense, expenseText) = createRefs()

            //region Balance
            Text(
                text = currentSymble(),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.constrainAs(moneyRefSymbol) {
                    top.linkTo(moneyValue.top)
                    bottom.linkTo(moneyValue.bottom)
                    end.linkTo(moneyValue.start, margin = 2.dp)
                })

            HideableTextField(
                text = formatValue(profileValue.value.accountValue()),
                hide = hideSensitiveData,
                style = MaterialTheme.typography.h3,
                modifier = Modifier.constrainAs(moneyValue) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    centerHorizontallyTo(parent)
                })
            Text(text = stringResource(id = R.string.total_balance),
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.constrainAs(balanceText) {
                    top.linkTo(moneyValue.bottom)
                    start.linkTo(moneyRefSymbol.start)
                    end.linkTo(moneyValue.end)
                })
            //endregion

            //region Income
            HideableTextField(
                text = profileValue.value.ethValue.toString(),
                hide = hideSensitiveData,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.constrainAs(moneyValueIncome) {
                    start.linkTo(moneySymbolIncome.end)
                    top.linkTo(balanceText.bottom)
                    bottom.linkTo(incomeIcon.bottom)
                })

            Text(
                text = currentSymble(),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.constrainAs(moneySymbolIncome) {
                    start.linkTo(parent.start)
                    top.linkTo(moneyValueIncome.top)
                    bottom.linkTo(moneyValueIncome.bottom)
                })

            Text(text = stringResource(id = R.string.eth_value),
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.constrainAs(incomeText) {
                    top.linkTo(moneyValueIncome.bottom)
                    end.linkTo(moneyValueIncome.end)
                    start.linkTo(moneySymbolIncome.start)
                })
            //endregion

            //region Expense

            Image(
                painter = painterResource(id = R.drawable.slp_icon),
                contentDescription = "",
                Modifier.constrainAs(slpIcon) {
                    top.linkTo(moneyValueExpense.top)
                    end.linkTo(moneyValueExpense.start)
                    bottom.linkTo(moneyValueExpense.bottom)
                }
            )

            HideableTextField(
                text = profileValue.value.totalSlp.toString(),
                hide = hideSensitiveData,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.constrainAs(moneyValueExpense) {
                    top.linkTo(moneyValueIncome.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(moneyValueIncome.bottom)
                })

            Text(text = stringResource(id = R.string.slp_quantity),
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.constrainAs(expenseText) {
                    start.linkTo(moneySymbolExpense.start)
                    top.linkTo(moneyValueExpense.bottom)
                    end.linkTo(moneyValueExpense.end)
                })
            //endregion
        }
    }
}


@Composable
@Preview
fun AccountValuePreviewHideData() {
    AxieControllerTheme {
        Surface {
            AccountValueComponent(
                hideSensitiveData = true,
                profileValue = mutableStateOf(AccountValueView(0.0, 0, 0.0, ""))
            )
        }
    }
}

@Composable
@Preview
fun AccountValuePreviewShowData() {
    AxieControllerTheme {
        Surface {
            AccountValueComponent(
                hideSensitiveData = false,
                profileValue = mutableStateOf(AccountValueView(0.0, 0, 0.0, ""))
            )
        }
    }
}
