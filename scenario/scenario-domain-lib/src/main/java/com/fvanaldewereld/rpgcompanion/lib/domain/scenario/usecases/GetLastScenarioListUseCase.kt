package com.fvanaldewereld.rpgcompanion.lib.domain.scenario.usecases

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository
import org.koin.core.context.GlobalContext

class GetLastScenarioListUseCase {

    private val localDbScenarioRepository: DbScenarioRepository by GlobalContext.get().inject()

    suspend operator fun invoke(number: Int): List<ScenarioModel> =
        localDbScenarioRepository.getLastScenarios(number = number)
}
