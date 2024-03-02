package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScenarioModel(
    val id: Long? = null,
    val author: AuthorModel? = null,
    val characters: CharactersModel? = null,
    val chapters: ChaptersModel? = null,
    val documentName: String? = "",
    val information: InformationModel? = null,
    val places: PlacesModel? = null,
    val summary: SummaryModel? = null,
    val title: TitleModel? = null,
) : Parcelable, Model
