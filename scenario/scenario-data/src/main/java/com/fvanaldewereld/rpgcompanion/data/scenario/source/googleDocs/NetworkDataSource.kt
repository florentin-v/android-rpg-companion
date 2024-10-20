package com.fvanaldewereld.rpgcompanion.data.scenario.source.googleDocs

import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioDto

fun interface NetworkDataSource {
    fun getGoogleDocsById(documentId: String): ScenarioDto
}
