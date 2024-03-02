package com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import java.net.URL

fun interface GoogleDocsRepository {
    suspend fun getScenarioByGdocsUrl(documentUrl: URL): ScenarioModel
}
