package br.com.mercury.axiecontroller.utils

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import br.com.mercury.axiecontroller.R
import br.com.mercury.axiecontroller.ui.theme.*

data class AxieType(
    @StringRes val tpye: Int,
    @DrawableRes val icon: Int,
    @ColorRes val color: Color
) {

    private enum class AxieTypes {
        Aquatic,
        Beast,
        Bird,
        Bug,
        Dawn,
        Dusk,
        Mech,
        Plant,
        Rept
    }

    companion object {
        fun builder(type: String): AxieType {
            return when (AxieTypes.valueOf(type)) {
                AxieTypes.Aquatic -> AxieType(
                    R.string.axie_type_aqua, R.drawable.aqua,
                    ColorAquatic
                )
                AxieTypes.Beast -> AxieType(R.string.axie_type_beast, R.drawable.beast, ColorBeast)
                AxieTypes.Bird -> AxieType(R.string.axie_type_bird, R.drawable.bird, ColorBird)
                AxieTypes.Bug -> AxieType(R.string.axie_type_bug, R.drawable.bug, ColorBug)
                AxieTypes.Dawn -> AxieType(R.string.axie_type_dawn, R.drawable.dawn, ColorDawn)
                AxieTypes.Dusk -> AxieType(R.string.axie_type_dusk, R.drawable.dusk, ColorDusk)
                AxieTypes.Mech -> AxieType(R.string.axie_type_mech, R.drawable.mech, ColorMech)
                AxieTypes.Plant -> AxieType(R.string.axie_type_plant, R.drawable.plant, ColorPlant)
                AxieTypes.Rept -> AxieType(R.string.axie_type_rept, R.drawable.rept, ColorRept)
            }
        }
    }
}
