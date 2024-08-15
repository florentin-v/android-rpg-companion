package com.fvanaldewereld.rpgcompanion.ui.home.component

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSessionModel(
    val id: Long,
    val title: String,
    val status: GameSessionStatus = GameSessionStatus.NOT_STARTED,
    val game: GameModel,
) : Parcelable

// TODO FVA Move into specifid module
