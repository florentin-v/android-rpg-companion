package com.fvanaldewereld.rpgcompanion.ui.home.model

import android.os.Parcelable
import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterUI(
    val id: Long,
    val name: String,
    val level: Int,
    val game: GameModel?,
) : Parcelable
