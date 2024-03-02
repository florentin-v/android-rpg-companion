package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDbObjectMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ChapterMapperTest {

    private lateinit var chapterMapper: ChapterMapper

    @BeforeEach
    fun setUp() {
        chapterMapper = ChapterMapperImpl()
    }

    @Test
    fun `WHEN map Chapter THEN return ChapterModel`() {
        // GIVEN

        // WHEN
        val scenario = chapterMapper.to(ScenarioDbObjectMockFactory.chapter)

        // THEN
        Assertions.assertEquals(scenario, ScenarioModelMockFactory.chapterModel)
    }

    @Test
    fun `WHEN map ChapterModel THEN return Chapter`() {
        // GIVEN

        // WHEN
        val scenario = chapterMapper.from(ScenarioModelMockFactory.chapterModel)

        // THEN
        Assertions.assertEquals(scenario, ScenarioDbObjectMockFactory.chapter)
    }
}
