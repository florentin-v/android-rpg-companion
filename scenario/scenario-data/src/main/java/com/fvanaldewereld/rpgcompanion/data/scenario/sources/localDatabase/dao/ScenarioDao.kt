package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao

import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.relations.Scenario

interface ScenarioDao {
    fun insertScenario(scenario: Scenario): Long

    fun deleteScenario(scenario: Scenario): Long

    fun getAllScenarioList(): List<Scenario>

    fun getLastScenarioList(number: Int): List<Scenario>

    fun getScenarioByDocumentName(documentName: String): Scenario

    fun getScenarioById(id: Long): Scenario
}
