package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SummaryDto(
    val text: DescriptionDto? = null,
    val tt: String = "",
) : Parcelable, ScenarioElementDto
