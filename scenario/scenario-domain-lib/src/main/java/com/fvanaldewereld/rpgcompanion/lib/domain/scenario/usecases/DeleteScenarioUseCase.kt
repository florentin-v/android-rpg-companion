package com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository
import org.koin.core.context.GlobalContext

class DeleteScenarioUseCase {

    private val localDbScenarioRepository: DbScenarioRepository by GlobalContext.get().inject()

    suspend operator fun invoke(scenarioId: Long): Long = localDbScenarioRepository.deleteById(scenarioId)
}
