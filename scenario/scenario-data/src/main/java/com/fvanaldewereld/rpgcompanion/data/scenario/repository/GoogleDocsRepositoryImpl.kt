package com.fvanaldewereld.rpgcompanion.data.scenario.repository

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.GoogleDocsRepository
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.ScenarioModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.source.googleDocs.GoogleDocsDataSource
import com.fvanaldewereld.rpgcompanion.data.scenario.source.googleDocs.extension.extractGoogleDocumentId
import java.net.URL

internal class GoogleDocsRepositoryImpl(
    private val googleDocsDataSource: GoogleDocsDataSource,
    private val scenarioModelMapper: ScenarioModelMapper,
) : GoogleDocsRepository {

    override suspend fun getScenarioByGdocsUrl(documentUrl: URL): ScenarioModel {
        val scenarioDto = googleDocsDataSource.getGoogleDocsById(documentUrl.extractGoogleDocumentId())
        return scenarioModelMapper.toDomain(scenarioDto)
    }
}
