package com.fvanaldewereld.rpgcompanion.api.domain.game.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameModel(
    val id: Long = 0,
    val name: String,
) : Parcelable
