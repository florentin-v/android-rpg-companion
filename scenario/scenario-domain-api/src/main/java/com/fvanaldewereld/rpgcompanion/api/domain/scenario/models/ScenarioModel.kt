package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScenarioModel(
    val id: Long? = null,
    val documentName: String,
    val chapters: ChaptersModel = ChaptersModel(),
    val characters: CharactersModel = CharactersModel(),
    val mainInfo: MainInfoModel = MainInfoModel(),
    val places: PlacesModel = PlacesModel(),
) : Parcelable, Model
