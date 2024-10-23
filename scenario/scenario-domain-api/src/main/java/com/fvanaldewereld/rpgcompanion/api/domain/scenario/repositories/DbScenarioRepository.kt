package com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel

interface DbScenarioRepository {

    suspend fun addScenario(scenarioModel: ScenarioModel): Long

    suspend fun getAllScenarioList(): List<ScenarioModel>

    suspend fun getLastScenarioList(number: Int): List<ScenarioModel>

    suspend fun getScenarioByDocumentName(documentName: String): ScenarioModel

    suspend fun getScenarioById(id: Long): ScenarioModel

    suspend fun deleteById(id: Long): Long
}
