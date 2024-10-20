package com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.ChapterLocalDb
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.CharacterLocalDb
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.PlaceLocalDb
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.ScenarioBaseLocalDb

data class ScenarioLocalDb(
    @Embedded val scenarioBaseLocalDb: ScenarioBaseLocalDb,
    @Relation(
        parentColumn = "id",
        entityColumn = "scenario_id",
    )
    val chapterLocalDbList: List<ChapterLocalDb> = emptyList(),
    @Relation(
        parentColumn = "id",
        entityColumn = "scenario_id",
    )
    val characterLocalDbList: List<CharacterLocalDb> = emptyList(),
    @Relation(
        parentColumn = "id",
        entityColumn = "scenario_id",
    )
    val placeLocalDbList: List<PlaceLocalDb> = emptyList(),
)
