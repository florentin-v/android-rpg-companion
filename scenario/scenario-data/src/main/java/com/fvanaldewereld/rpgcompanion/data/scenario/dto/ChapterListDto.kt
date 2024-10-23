package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChapterListDto(
    val list: List<ChapterDto>,
) : Parcelable, ScenarioElementDto
