package com.fvanaldewereld.rpgcompanion.ui.home.component

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    val id: Long,
    val name: String,
    val level: Int,
    val game: GameModel,
) : Parcelable

// TODO FVA Move into specifid module
