package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthorModel(
    val name: String? = null,
) : Parcelable, ScenarioElementModel
