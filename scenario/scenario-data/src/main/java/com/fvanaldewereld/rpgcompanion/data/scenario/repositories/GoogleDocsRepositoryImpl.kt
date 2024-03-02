package com.fvanaldewereld.rpgcompanion.data.scenario.repositories

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.GoogleDocsRepository
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.ScenarioModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.googleDocs.GoogleDocsDataSource
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.googleDocs.extensions.extractGoogleDocumentId
import org.koin.core.context.GlobalContext
import java.net.URL

internal class GoogleDocsRepositoryImpl : GoogleDocsRepository {

    private val googleDocsDataSource: GoogleDocsDataSource by GlobalContext.get().inject()
    private val scenarioModelMapper: ScenarioModelMapper by GlobalContext.get().inject()

    override suspend fun getScenarioByGdocsUrl(documentUrl: URL): ScenarioModel {
        val scenarioDto = googleDocsDataSource.getGoogleDocsById(documentUrl.extractGoogleDocumentId())
        return scenarioModelMapper.to(scenarioDto)
    }
}
