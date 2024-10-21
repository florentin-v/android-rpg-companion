package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChapterDto(
    val name: String,
    val description: DescriptionDto,
) : Parcelable
