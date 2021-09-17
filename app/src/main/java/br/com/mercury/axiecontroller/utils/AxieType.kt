package br.com.mercury.axiecontroller.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.mercury.axiecontroller.R

data class AxieType(
    @StringRes val tpye: Int,
    @DrawableRes val icon: Int
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
                AxieTypes.Aquatic -> AxieType(R.string.axie_type_aqua, R.drawable.aqua)
                AxieTypes.Beast -> AxieType(R.string.axie_type_beast, R.drawable.beast)
                AxieTypes.Bird -> AxieType(R.string.axie_type_bird, R.drawable.bird)
                AxieTypes.Bug -> AxieType(R.string.axie_type_bug, R.drawable.bug)
                AxieTypes.Dawn -> AxieType(R.string.axie_type_dawn, R.drawable.dawn)
                AxieTypes.Dusk -> AxieType(R.string.axie_type_dusk, R.drawable.dusk)
                AxieTypes.Mech -> AxieType(R.string.axie_type_mech, R.drawable.mech)
                AxieTypes.Plant -> AxieType(R.string.axie_type_plant, R.drawable.plant)
                AxieTypes.Rept -> AxieType(R.string.axie_type_rept, R.drawable.rept)
            }
        }
    }
}
