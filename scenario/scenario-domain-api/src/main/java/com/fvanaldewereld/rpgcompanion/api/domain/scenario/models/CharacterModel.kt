package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterModel(
    val name: String? = "",
    val description: DescriptionModel? = null,
) : Parcelable, Model
