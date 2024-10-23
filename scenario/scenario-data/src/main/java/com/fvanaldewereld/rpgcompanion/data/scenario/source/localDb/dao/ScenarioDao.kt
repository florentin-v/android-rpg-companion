package com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao

import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.relation.ScenarioLocalDb

interface ScenarioDao {
    fun insertScenario(scenarioLocalDb: ScenarioLocalDb): Long

    fun deleteScenario(scenarioLocalDb: ScenarioLocalDb): Long

    fun getAllScenarioList(): List<ScenarioLocalDb>

    fun getLastScenarioList(number: Int): List<ScenarioLocalDb>

    fun getScenarioByDocumentName(documentName: String): ScenarioLocalDb

    fun getScenarioById(id: Long): ScenarioLocalDb
}
