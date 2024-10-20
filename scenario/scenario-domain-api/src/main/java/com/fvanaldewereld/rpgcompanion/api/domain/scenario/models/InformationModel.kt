package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InformationModel(
    val genreList: GenreListModel = GenreListModel(),
    val nbPlayers: Int = 1,
    val themeList: ThemeListModel = ThemeListModel(),
) : Parcelable, ScenarioElementModel
