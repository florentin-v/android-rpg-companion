package com.fvanaldewereld.rpgcompanion.data.scenario.source.googleDocs

import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioDto
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.dto.ScenarioDtoMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.source.googleDocs.service.GoogleDocsService

interface GoogleDocsDataSource : NetworkDataSource

internal class GoogleDocsDataSourceImpl(
    private val googleDocsService: GoogleDocsService,
    private val scenarioDtoMapper: ScenarioDtoMapper,
) : GoogleDocsDataSource {

    override fun getGoogleDocsById(documentId: String): ScenarioDto =
        googleDocsService
            .getDocs()
            .documents()[documentId]
            .execute()
            .let(scenarioDtoMapper::toDataDto)
}
