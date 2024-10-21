package com.fvanaldewereld.rpgcompanion.ui.home.state

import android.os.Parcelable
import com.fvanaldewereld.rpgcompanion.ui.home.model.CharacterUI
import kotlinx.parcelize.Parcelize

sealed class LastCharacterListState : Parcelable {
    @Parcelize
    data object Loading : LastCharacterListState()

    @Parcelize
    data class Success(val lastCharacterList: List<CharacterUI>) : LastCharacterListState()

    @Parcelize
    data class Failure(val exception: Throwable) : LastCharacterListState()
}
