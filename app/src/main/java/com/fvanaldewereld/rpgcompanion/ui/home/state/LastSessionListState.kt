package com.fvanaldewereld.rpgcompanion.ui.home.state

import android.os.Parcelable
import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import kotlinx.parcelize.Parcelize

sealed class LastSessionListState : Parcelable {
    @Parcelize
    data object Loading : LastSessionListState()

    @Parcelize
    data class Success(val lastSessionList: List<SessionModel>) : LastSessionListState()

    @Parcelize
    data class Failure(val exception: Throwable) : LastSessionListState()
}
