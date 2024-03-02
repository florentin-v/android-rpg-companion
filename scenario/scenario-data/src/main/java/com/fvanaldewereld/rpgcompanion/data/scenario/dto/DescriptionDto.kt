package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DescriptionDto(
    val paragraphs: List<String>? = emptyList(),
) : Parcelable, Dto
