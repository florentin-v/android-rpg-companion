package com.fvanaldewereld.rpgcompanion.data.scenario.repository

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.ScenarioMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao.ScenarioDao

internal class LocalDbScenarioRepository(
    private val scenarioDao: ScenarioDao,
    private val scenarioMapper: ScenarioMapper,
) : DbScenarioRepository {

    override suspend fun addScenario(scenarioModel: ScenarioModel): Long =
        scenarioDao.insertScenario(
            scenarioLocalDb = scenarioMapper.toDataLocalDb(scenarioModel),
        )

    override suspend fun getAllScenarioList(): List<ScenarioModel> =
        scenarioDao.getAllScenarioList().map(scenarioMapper::toDomain)

    override suspend fun getLastScenarioList(number: Int): List<ScenarioModel> =
        scenarioDao.getLastScenarioList(number).map(scenarioMapper::toDomain)

    override suspend fun getScenarioByDocumentName(documentName: String): ScenarioModel =
        scenarioDao.getScenarioByDocumentName(
            documentName = documentName,
        ).let(scenarioMapper::toDomain)

    override suspend fun getScenarioById(id: Long): ScenarioModel =
        scenarioMapper.toDomain(
            from = scenarioDao.getScenarioById(id = id),
        )

    override suspend fun deleteById(id: Long): Long =
        scenarioDao.deleteScenario(
            scenarioLocalDb = scenarioDao.getScenarioById(id = id),
        )
}
