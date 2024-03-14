package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.Chapter
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.Character
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.Place
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.ScenarioBase

data class Scenario(
    @Embedded val scenarioBase: ScenarioBase,
    @Relation(
        parentColumn = "id",
        entityColumn = "scenarioId"
    )
    val chapters: List<Chapter> = emptyList(),
    @Relation(
        parentColumn = "id",
        entityColumn = "scenarioId"
    )
    val characters: List<Character> = emptyList(),
    @Relation(
        parentColumn = "id",
        entityColumn = "scenarioId"
    )
    val places: List<Place> = emptyList(),
) : LocalDbRelation
