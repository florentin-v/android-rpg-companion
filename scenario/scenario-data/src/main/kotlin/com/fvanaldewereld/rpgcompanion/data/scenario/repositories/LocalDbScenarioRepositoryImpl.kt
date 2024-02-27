package com.fvanaldewereld.rpgcompanion.data.scenario.repositories

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.ScenarioMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.ScenarioDao
import org.koin.core.context.GlobalContext

class LocalDbScenarioRepositoryImpl : DbScenarioRepository {

    private val scenarioDao: ScenarioDao by GlobalContext.get().inject()
    private val scenarioMapperImpl: ScenarioMapper by GlobalContext.get().inject()

    override suspend fun addScenario(scenarioModel: ScenarioModel): Long =
        scenarioDao.insertScenario(
            scenario = scenarioMapperImpl.from(scenarioModel),
        )

    override suspend fun getAllScenarios(): List<ScenarioModel> =
        scenarioDao.getAllScenarios().map {
            scenarioMapperImpl.to(from = it)
        }

    override suspend fun getScenarioByDocumentName(documentName: String): ScenarioModel =
        scenarioMapperImpl.to(
            from = scenarioDao.getScenarioByDocumentName(
                documentName = documentName,
            ),
        )

    override suspend fun getScenarioById(id: Long): ScenarioModel =
        scenarioMapperImpl.to(
            from = scenarioDao.getScenarioById(id = id),
        )

    override suspend fun deleteById(id: Long): Long =
        scenarioDao.deleteScenario(
            scenario = scenarioDao.getScenarioById(id = id),
        )
}
