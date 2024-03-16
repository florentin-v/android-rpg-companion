package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharactersModel(
    val characters: List<CharacterModel> = emptyList(),
) : Parcelable, ScenarioElementModel
