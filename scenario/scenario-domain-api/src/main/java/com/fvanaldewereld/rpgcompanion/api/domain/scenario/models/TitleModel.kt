package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TitleModel(
    val value: String? = null,
) : Parcelable, ScenarioElementModel
