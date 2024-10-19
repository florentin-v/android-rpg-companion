package com.fvanaldewereld.rpgcompanion.api.domain.character.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    val id: Long = 0,
    val gameId: Long? = null,
    val level: Int,
    val name: String,
) : Model, Parcelable
