package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScenarioModel(
    val id: Long? = null,
    val documentName: String,
    val chapterList: ChapterListModel = ChapterListModel(),
    val characterList: CharacterListModel = CharacterListModel(),
    val mainInfo: MainInfoModel = MainInfoModel(),
    val placeList: PlaceListModel = PlaceListModel(),
) : Parcelable, Model
