package com.fvanaldewereld.rpgcompanion.api.domain.scenario.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainInfoModel(
    val author: AuthorModel = AuthorModel(),
    val information: InformationModel = InformationModel(),
    val summary: SummaryModel = SummaryModel(),
    val title: TitleModel = TitleModel(),
) : Parcelable, Model
