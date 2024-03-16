package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InformationDto(
    val genres: List<String> = emptyList(),
    val nbPlayers: Int = 1,
    val themes: List<String> = emptyList(),
) : Parcelable, ScenarioElementDto
