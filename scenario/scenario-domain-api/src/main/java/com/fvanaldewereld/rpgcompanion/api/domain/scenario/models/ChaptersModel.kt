package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChaptersModel(
    val chapters: List<ChapterModel>? = emptyList(),
) : Parcelable, ScenarioElementModel
