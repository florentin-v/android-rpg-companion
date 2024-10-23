package com.fvanaldewereld.rpgcompanion.lib.domain.scenario.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.GoogleDocsRepository
import java.net.URL

class GetScenarioByUrlUseCase(
    private val googleDocsRepository: GoogleDocsRepository,
) {

    suspend operator fun invoke(documentUrl: URL): ScenarioModel =
        googleDocsRepository.getScenarioByGdocsUrl(documentUrl = documentUrl)
}
