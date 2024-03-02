package com.fvanaldewereld.rpgcompanion.data.scenario.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthorDto(
    val name: String? = "",
) : Parcelable, ScenarioElementDto
