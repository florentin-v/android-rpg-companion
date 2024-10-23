package com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao

import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.relation.ScenarioLocalDb

class ScenarioDaoImpl(
    private val scenarioBaseDao: ScenarioBaseDao,
    private val chapterDao: ChapterDao,
    private val characterDao: CharacterDao,
    private val placeDao: PlaceDao,
) : ScenarioDao {

    override fun insertScenario(scenarioLocalDb: ScenarioLocalDb): Long {
        val scenarioBaseId = scenarioBaseDao.insertScenarioBase(scenarioLocalDb.scenarioBaseLocalDb)
        scenarioLocalDb.run {
            chapterLocalDbList.map { it.scenarioId = scenarioBaseId }
            chapterDao.insertAll(chapterLocalDbList)
            characterLocalDbList.map { it.scenarioId = scenarioBaseId }
            characterDao.insertAll(characterLocalDbList)
            placeLocalDbList.map { it.scenarioId = scenarioBaseId }
            placeDao.insertAll(placeLocalDbList)
        }
        return scenarioBaseId
    }

    override fun deleteScenario(scenarioLocalDb: ScenarioLocalDb): Long {
        scenarioBaseDao.deleteScenarioBase(scenarioBaseLocalDb = scenarioLocalDb.scenarioBaseLocalDb)
        scenarioLocalDb.apply {
            chapterLocalDbList.map { chapterDao.delete(it) }
            characterLocalDbList.map { characterDao.delete(it) }
            placeLocalDbList.map { placeDao.delete(it) }
        }
        return scenarioLocalDb.scenarioBaseLocalDb.id
    }

    override fun getAllScenarioList(): List<ScenarioLocalDb> = scenarioBaseDao.getAllScenarioListBase()
        .map { scenarioBase ->
            ScenarioLocalDb(
                scenarioBaseLocalDb = scenarioBase,
                chapterLocalDbList = chapterDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                characterLocalDbList = characterDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                placeLocalDbList = placeDao.getAllByScenarioId(scenarioId = scenarioBase.id),
            )
        }

    override fun getLastScenarioList(number: Int): List<ScenarioLocalDb> =
        scenarioBaseDao.getLastScenarioListBase(number)
            .map { scenarioBase ->
                ScenarioLocalDb(
                    scenarioBaseLocalDb = scenarioBase,
                    chapterLocalDbList = chapterDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                    characterLocalDbList = characterDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                    placeLocalDbList = placeDao.getAllByScenarioId(scenarioId = scenarioBase.id),
                )
            }

    override fun getScenarioByDocumentName(documentName: String): ScenarioLocalDb =
        with(scenarioBaseDao.getScenarioBaseByDocumentName(documentName = documentName)) {
            ScenarioLocalDb(
                scenarioBaseLocalDb = this,
                chapterLocalDbList = chapterDao.getAllByScenarioId(scenarioId = this.id),
                characterLocalDbList = characterDao.getAllByScenarioId(scenarioId = this.id),
                placeLocalDbList = placeDao.getAllByScenarioId(scenarioId = this.id),
            )
        }

    override fun getScenarioById(id: Long): ScenarioLocalDb = with(scenarioBaseDao.getScenarioBaseById(id)) {
        ScenarioLocalDb(
            scenarioBaseLocalDb = this,
            chapterLocalDbList = chapterDao.getAllByScenarioId(scenarioId = this.id),
            characterLocalDbList = characterDao.getAllByScenarioId(scenarioId = this.id),
            placeLocalDbList = placeDao.getAllByScenarioId(scenarioId = this.id),
        )
    }
}
