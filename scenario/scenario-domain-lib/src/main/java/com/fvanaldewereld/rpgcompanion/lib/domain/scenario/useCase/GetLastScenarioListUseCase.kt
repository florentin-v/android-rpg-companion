package com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository

class GetLastScenarioListUseCase(
    private val localDbScenarioRepository: DbScenarioRepository,
) {

    suspend operator fun invoke(number: Int): List<ScenarioModel> =
        localDbScenarioRepository.getLastScenarioList(number = number)
}
