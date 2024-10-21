package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InformationDto(
    val genreList: List<String> = emptyList(),
    val nbPlayers: Int = 1,
    val themeList: List<String> = emptyList(),
) : Parcelable, ScenarioElementDto
