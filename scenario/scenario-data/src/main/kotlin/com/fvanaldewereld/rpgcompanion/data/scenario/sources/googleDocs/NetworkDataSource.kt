package com.fvanaldewereld.rpgcompanion.data.scenario.sources.googleDocs

import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioDto

fun interface NetworkDataSource {
    fun getGoogleDocsById(documentId: String): ScenarioDto
}
