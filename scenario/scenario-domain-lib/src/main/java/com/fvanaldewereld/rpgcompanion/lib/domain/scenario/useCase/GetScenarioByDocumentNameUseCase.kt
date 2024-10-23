package com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository

class GetScenarioByDocumentNameUseCase(
    private val dbScenarioRepository: DbScenarioRepository,
) {

    suspend operator fun invoke(id: Long): ScenarioModel =
        dbScenarioRepository.getScenarioById(id = id)
}
