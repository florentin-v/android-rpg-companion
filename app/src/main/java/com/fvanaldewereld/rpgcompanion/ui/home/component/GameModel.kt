package com.fvanaldewereld.rpgcompanion.ui.home.component

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameModel(
    val id: Long,
    val name: String,
) : Parcelable

// TODO FVA Move into specifid module
