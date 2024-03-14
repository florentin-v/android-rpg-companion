package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChapterModel(
    val name: String,
    val description: DescriptionModel,
) : Parcelable, Model
