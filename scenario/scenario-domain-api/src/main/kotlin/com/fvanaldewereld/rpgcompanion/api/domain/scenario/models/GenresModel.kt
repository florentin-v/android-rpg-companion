package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class GenresModel(
    val genres: List<String> = emptyList(),
) : Parcelable, Model