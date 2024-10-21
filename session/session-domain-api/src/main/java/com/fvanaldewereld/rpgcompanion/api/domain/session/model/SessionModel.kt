package com.fvanaldewereld.rpgcompanion.api.domain.session.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SessionModel(
    val id: Long = 0,
    val gameId: Long? = null,
    val status: SessionStatus,
    val title: String,
) : Parcelable
