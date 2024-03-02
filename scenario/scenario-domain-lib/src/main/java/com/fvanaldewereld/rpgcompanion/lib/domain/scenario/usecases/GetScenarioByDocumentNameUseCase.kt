package com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository
import org.koin.core.context.GlobalContext

class GetScenarioByDocumentNameUseCase {
    private val dbScenarioRepository: DbScenarioRepository by GlobalContext.get().inject()

    suspend operator fun invoke(id: Long): ScenarioModel =
        dbScenarioRepository.getScenarioById(id = id)
}
