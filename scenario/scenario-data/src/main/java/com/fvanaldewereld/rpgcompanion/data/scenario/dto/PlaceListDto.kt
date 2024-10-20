package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlaceListDto(
    val list: List<PlaceDto>,
) : Parcelable, ScenarioElementDto
