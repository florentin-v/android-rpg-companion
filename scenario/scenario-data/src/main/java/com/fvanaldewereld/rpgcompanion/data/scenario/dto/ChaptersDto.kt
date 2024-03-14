package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChaptersDto(
    val chapters: List<ChapterDto>,
) : Parcelable, ScenarioElementDto
