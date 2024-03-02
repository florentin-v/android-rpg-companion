package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlacesModel(
    val places: List<PlaceModel>? = emptyList(),
) : Parcelable, ScenarioElementModel
