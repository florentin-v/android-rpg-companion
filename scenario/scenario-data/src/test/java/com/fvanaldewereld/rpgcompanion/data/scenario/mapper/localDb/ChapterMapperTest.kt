package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDbObjectMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ChapterMapperTest {

    private lateinit var chapterMapper: ChapterMapper

    @BeforeEach
    fun setUp() {
        chapterMapper = ChapterMapper()
    }

    @Test
    fun `WHEN map Chapter THEN return ChapterModel`() {
        // GIVEN

        // WHEN
        val scenario = chapterMapper.toDomain(ScenarioDbObjectMockFactory.chapterLocalDb)

        // THEN
        Assertions.assertEquals(scenario, ScenarioModelMockFactory.chapterModel)
    }

    @Test
    fun `WHEN map ChapterModel THEN return Chapter`() {
        // GIVEN

        // WHEN
        val scenario = chapterMapper.toDataLocalDb(ScenarioModelMockFactory.chapterModel)

        // THEN
        Assertions.assertEquals(scenario, ScenarioDbObjectMockFactory.chapterLocalDb)
    }
}
