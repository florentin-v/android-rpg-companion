package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SummaryDto(
    val text: DescriptionDto,
) : Parcelable, ScenarioElementDto {
    constructor(paragraphs: List<String>) : this(text = DescriptionDto(paragraphs))
}
