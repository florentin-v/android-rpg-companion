package com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository

class DeleteScenarioUseCase(
    private val localDbScenarioRepository: DbScenarioRepository,
) {

    suspend operator fun invoke(scenarioId: Long): Long =
        localDbScenarioRepository.deleteById(scenarioId)
}
