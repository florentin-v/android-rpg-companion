package com.fvanaldewereld.rpgcompanion.data.scenario.sources.googleDocs

import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioDto
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dtoMappers.ScenarioDtoMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.googleDocs.service.GoogleDocsService
import org.koin.core.context.GlobalContext

interface GoogleDocsDataSource : NetworkDataSource

internal class GoogleDocsDataSourceImpl : GoogleDocsDataSource {

    private val googleDocsService: GoogleDocsService by GlobalContext.get().inject()
    private val scenarioDtoMapper: ScenarioDtoMapper by GlobalContext.get().inject()

    override fun getGoogleDocsById(documentId: String): ScenarioDto {
        val document = googleDocsService.getDocs().documents()[documentId].execute()
        return scenarioDtoMapper.to(document)
    }
}
