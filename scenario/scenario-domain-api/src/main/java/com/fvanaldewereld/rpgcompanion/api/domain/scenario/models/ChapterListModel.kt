package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChapterListModel(
    val list: List<ChapterModel> = emptyList(),
) : Parcelable, ScenarioElementModel
