package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao

import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.relations.Scenario
import org.koin.core.context.GlobalContext

interface ScenarioDao {
    fun insertScenario(scenario: Scenario): Long

    fun deleteScenario(scenario: Scenario): Long

    fun getAllScenarios(): List<Scenario>

    fun getScenarioByDocumentName(documentName: String): Scenario

    fun getScenarioById(id: Long): Scenario
}

class ScenarioDaoImpl : ScenarioDao {

    private val scenarioBaseDao: ScenarioBaseDao by GlobalContext.get().inject()
    private val chapterDao: ChapterDao by GlobalContext.get().inject()
    private val characterDao: CharacterDao by GlobalContext.get().inject()
    private val placeDao: PlaceDao by GlobalContext.get().inject()

    override fun insertScenario(scenario: Scenario): Long {
        val scenarioBaseId = scenarioBaseDao.insertScenarioBase(scenario.scenarioBase)
        scenario.chapters?.let { chapters ->
            run {
                chapters.map { it.scenarioId = scenarioBaseId }
                chapterDao.insertAll(chapters)
            }
        }
        scenario.characters?.let { characters ->
            run {
                characters.map { it.scenarioId = scenarioBaseId }
                characterDao.insertAll(characters)
            }
        }
        scenario.places?.let { places ->
            run {
                places.map { it.scenarioId = scenarioBaseId }
                placeDao.insertAll(places)
            }
        }
        return scenarioBaseId
    }

    override fun deleteScenario(scenario: Scenario): Long {
        scenarioBaseDao.deleteScenarioBase(scenarioBase = scenario.scenarioBase)
        scenario.chapters?.map { chapterDao.delete(it) }
        scenario.characters?.map { characterDao.delete(it) }
        scenario.places?.map { placeDao.delete(it) }
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

    override fun getScenarioByDocumentName(documentName: String): Scenario =
        scenarioBaseDao.getScenarioBaseByDocumentName(documentName = documentName)
            .let { scenarioBase ->
                Scenario(
                    scenarioBase = scenarioBase,
                    chapters = chapterDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                    characters = characterDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                    places = placeDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                )
            }

    override fun getScenarioById(id: Long): Scenario = scenarioBaseDao.getScenarioBaseById(id)
        .let { scenarioBase ->
            Scenario(
                scenarioBase = scenarioBase,
                chapters = chapterDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                characters = characterDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                places = placeDao.getAllByScenarioId(scenarioId = scenarioBase.id),
            )
        }
}
