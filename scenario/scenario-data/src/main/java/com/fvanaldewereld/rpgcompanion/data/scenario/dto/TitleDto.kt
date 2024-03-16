package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TitleDto(
    val value: String,
) : Parcelable, ScenarioElementDto
