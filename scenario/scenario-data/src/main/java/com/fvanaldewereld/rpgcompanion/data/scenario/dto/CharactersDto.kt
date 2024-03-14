package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharactersDto(
    val characters: List<CharacterDto>,
) : Parcelable, ScenarioElementDto
