package com.fvanaldewereld.rpgcompanion.ui.home.model

sealed interface HomeScreenAction {
    sealed interface TopAppBarAction : HomeScreenAction {
        data object SearchPressed : TopAppBarAction
        data object ProfilePressed : TopAppBarAction
    }

    sealed interface BottomNavBarAction : HomeScreenAction {
        data object ScenariosPressed : BottomNavBarAction
        data object LibraryPressed : BottomNavBarAction
        data object HomePressed : BottomNavBarAction
        data object GamesPressed : BottomNavBarAction
        data object CharactersPressed : BottomNavBarAction
    }

    data class LastGameSessionPressed(val id: Long) : HomeScreenAction
    data class LastCharacterPressed(val id: Long) : HomeScreenAction
    data class LastScenarioPressed(val id: Long) : HomeScreenAction

}
