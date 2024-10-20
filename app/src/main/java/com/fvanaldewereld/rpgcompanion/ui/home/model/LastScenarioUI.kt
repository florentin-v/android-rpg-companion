package com.fvanaldewereld.rpgcompanion.ui.home.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LastScenarioUI(
    val id: Long,
    val author: String,
    val title: String,
) : Parcelable
