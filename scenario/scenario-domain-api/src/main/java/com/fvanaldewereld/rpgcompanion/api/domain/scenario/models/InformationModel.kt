package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InformationModel(
    val genres: GenresModel = GenresModel(),
    val nbPlayers: Int = 1,
    val themes: ThemesModel = ThemesModel(),
) : Parcelable, ScenarioElementModel
