package com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository

class AddScenarioUseCase(
    private val localDbScenarioRepository: DbScenarioRepository,
) {

    suspend operator fun invoke(scenarioModel: ScenarioModel): Long =
        localDbScenarioRepository.addScenario(scenarioModel = scenarioModel)
}
