package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScenarioDto(
    val author: AuthorDto? = null,
    val characterList: CharacterListDto? = null,
    val chapterList: ChapterListDto? = null,
    val documentName: String,
    val information: InformationDto? = InformationDto(),
    val placeList: PlaceListDto? = null,
    val summary: SummaryDto? = null,
    val title: TitleDto? = null,
) : Parcelable
