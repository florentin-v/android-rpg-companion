package com.fvanaldewereld.rpgcompanion.ui.home.state

import android.os.Parcelable
import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import kotlinx.parcelize.Parcelize

sealed class LastGameListState : Parcelable {
    @Parcelize
    data object Loading : LastGameListState()

    @Parcelize
    data class Success(val lastGameList: List<GameModel>) : LastGameListState()

    @Parcelize
    data class Failure(val exception: Throwable) : LastGameListState()
}
