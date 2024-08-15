package com.fvanaldewereld.rpgcompanion.ui.home.state

import android.os.Parcelable
import com.fvanaldewereld.rpgcompanion.ui.home.component.CharacterModel
import com.fvanaldewereld.rpgcompanion.ui.home.component.GameModel
import com.fvanaldewereld.rpgcompanion.ui.home.component.GameSessionModel
import com.fvanaldewereld.rpgcompanion.ui.home.model.LastScenarioUI
import kotlinx.parcelize.Parcelize

sealed interface HomeUIState : Parcelable {
    @Parcelize
    data object Loading : HomeUIState

    @Parcelize
    data class Success(
        val lastCharacterModels: List<CharacterModel>,
        val lastGameModels: List<GameModel>,
        val lastGameSessionModels: List<GameSessionModel>,
        val lastScenarioUIs: List<LastScenarioUI>,
    ) : HomeUIState
}
