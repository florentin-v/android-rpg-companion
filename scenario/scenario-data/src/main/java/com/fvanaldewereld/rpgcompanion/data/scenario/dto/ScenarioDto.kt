package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScenarioDto(
    val author: AuthorDto? = null,
    val characters: CharactersDto? = null,
    val chapters: ChaptersDto? = null,
    val documentName: String,
    val information: InformationDto? = InformationDto(),
    val places: PlacesDto? = null,
    val summary: SummaryDto? = null,
    val title: TitleDto? = null,
) : Parcelable, Dto
