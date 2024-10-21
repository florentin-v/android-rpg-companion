package com.fvanaldewereld.rpgcompanion.ui.home.state

import android.os.Parcelable
import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import com.fvanaldewereld.rpgcompanion.ui.home.model.CharacterUI
import com.fvanaldewereld.rpgcompanion.ui.home.model.LastScenarioUI
import kotlinx.parcelize.Parcelize

sealed interface HomeUIState : Parcelable {
    @Parcelize
    data object Loading : HomeUIState

    @Parcelize
    data class Success(
        val lastCharacterUIList: List<CharacterUI>,
        val lastGameModelList: List<GameModel>,
        val lastSessionModelList: List<SessionModel>,
        val lastScenarioUIList: List<LastScenarioUI>,
    ) : HomeUIState
}
