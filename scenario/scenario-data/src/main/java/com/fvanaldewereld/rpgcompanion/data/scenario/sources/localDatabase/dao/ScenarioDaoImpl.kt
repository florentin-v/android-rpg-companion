package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao

import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.relations.Scenario
import org.koin.core.context.GlobalContext

class ScenarioDaoImpl : ScenarioDao {

    private val scenarioBaseDao: ScenarioBaseDao by GlobalContext.get().inject()
    private val chapterDao: ChapterDao by GlobalContext.get().inject()
    private val characterDao: CharacterDao by GlobalContext.get().inject()
    private val placeDao: PlaceDao by GlobalContext.get().inject()

    override fun insertScenario(scenario: Scenario): Long {
        val scenarioBaseId = scenarioBaseDao.insertScenarioBase(scenario.scenarioBase)
        scenario.run {
            chapters.map { it.scenarioId = scenarioBaseId }
            chapterDao.insertAll(chapters)
            characters.map { it.scenarioId = scenarioBaseId }
            characterDao.insertAll(characters)
            places.map { it.scenarioId = scenarioBaseId }
            placeDao.insertAll(places)
        }
        return scenarioBaseId
    }

    override fun deleteScenario(scenario: Scenario): Long {
        scenarioBaseDao.deleteScenarioBase(scenarioBase = scenario.scenarioBase)
        scenario.apply {
            chapters.map { chapterDao.delete(it) }
            characters.map { characterDao.delete(it) }
            places.map { placeDao.delete(it) }
        }
        return scenario.scenarioBase.id
    }

    override fun getAllScenarios(): List<Scenario> = scenarioBaseDao.getAllScenariosBase()
        .map { scenarioBase ->
            Scenario(
                scenarioBase = scenarioBase,
                chapters = chapterDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                characters = characterDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                places = placeDao.getAllByScenarioId(scenarioId = scenarioBase.id),
            )
        }

    override fun getLastScenarios(number: Int): List<Scenario> = scenarioBaseDao.getLastScenariosBase(number)
        .map { scenarioBase ->
            Scenario(
                scenarioBase = scenarioBase,
                chapters = chapterDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                characters = characterDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                places = placeDao.getAllByScenarioId(scenarioId = scenarioBase.id),
            )
        }

    override fun getScenarioByDocumentName(documentName: String): Scenario =
        with(scenarioBaseDao.getScenarioBaseByDocumentName(documentName = documentName)) {
            Scenario(
                scenarioBase = this,
                chapters = chapterDao.getAllByScenarioId(scenarioId = this.id),
                characters = characterDao.getAllByScenarioId(scenarioId = this.id),
                places = placeDao.getAllByScenarioId(scenarioId = this.id),
            )
        }

    override fun getScenarioById(id: Long): Scenario = with(scenarioBaseDao.getScenarioBaseById(id)) {
        Scenario(
            scenarioBase = this,
            chapters = chapterDao.getAllByScenarioId(scenarioId = this.id),
            characters = characterDao.getAllByScenarioId(scenarioId = this.id),
            places = placeDao.getAllByScenarioId(scenarioId = this.id),
        )
    }
}
