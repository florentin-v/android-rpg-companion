package com.fvanaldewereld.rpgcompanion.ui.home.viewModel.slice

import com.fvanaldewereld.rpgcompanion.lib.domain.character.useCase.AddCharacterUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.character.useCase.GetLastCharacterListUseCase
import com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase.GetGameByIdUseCase
import com.fvanaldewereld.rpgcompanion.ui.home.mapper.CharacterUIMapper
import com.fvanaldewereld.rpgcompanion.ui.home.state.LastCharacterListState
import com.fvanaldewereld.rpgcompanion.ui.home.viewModel.HomeViewModel.Companion.TmpMock
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GetLastCharacterListSliceImpl(
    // TODO FVA remove after implementing the character creation flow
    private val addCharacterUseCase: AddCharacterUseCase,
    private val getLastCharacterListUseCase: GetLastCharacterListUseCase,
    private val getGameByIdUseCase: GetGameByIdUseCase,
    private val characterUIMapper: CharacterUIMapper,
) : GetLastCharacterListSlice {
    private val _lastCharacterListState = MutableStateFlow<LastCharacterListState?>(null)
    override var lastCharacterListStateFlow = _lastCharacterListState.asStateFlow()

    override suspend fun getLastCharacterList(number: Int) {
        _lastCharacterListState.value = LastCharacterListState.Loading

        // TODO FVA Remove this after implementing the character creation flow
        runCatching {
            addCharacterUseCase(characterModel = TmpMock.lastCharacterModelList[0])
            addCharacterUseCase(characterModel = TmpMock.lastCharacterModelList[1])
            addCharacterUseCase(characterModel = TmpMock.lastCharacterModelList[2])
        }
        delay(2_000L)

        runCatching { getLastCharacterListUseCase(number = 2) }
            .onSuccess { lastCharacterModelList ->
                val lastCharacterUIList = lastCharacterModelList.map { characterModel ->
                    val gameModel = characterModel.gameId?.let {
                        runCatching { getGameByIdUseCase(it) }.getOrNull()
                    }
                    characterUIMapper.toUIWithGameModel(
                        from = characterModel,
                        gameModel = gameModel,
                    )
                }
                _lastCharacterListState.value = LastCharacterListState.Success(lastCharacterList = lastCharacterUIList)
            }
            .onFailure { _lastCharacterListState.value = LastCharacterListState.Failure(it) }
    }
}
