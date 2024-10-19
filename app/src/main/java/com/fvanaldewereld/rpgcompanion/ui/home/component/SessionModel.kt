package com.fvanaldewereld.rpgcompanion.ui.home.component

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SessionModel(
    val id: Long,
    val title: String,
    val status: SessionStatus = SessionStatus.NOT_STARTED,
    val game: GameModel,
) : Parcelable

// TODO FVA Move into specific module
