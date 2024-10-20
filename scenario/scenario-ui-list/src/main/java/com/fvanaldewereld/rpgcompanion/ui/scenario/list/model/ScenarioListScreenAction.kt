package com.fvanaldewereld.rpgcompanion.ui.scenario.list.model

sealed interface ScenarioListScreenAction {
    data object OnBackPressedButton : ScenarioListScreenAction

    data class AddScenario(val url: String) : ScenarioListScreenAction
    data class DeleteScenario(val id: Long) : ScenarioListScreenAction
    data class GoToScenarioDetail(val id: Long) : ScenarioListScreenAction
}
