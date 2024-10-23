package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TitleMapperTest {

    private lateinit var titleMapper: TitleMapper

    @BeforeEach
    fun setUp() {
        titleMapper = TitleMapper()
    }

    @Test
    fun `WHEN map TitleDto THEN return TitleModel`() {
        // GIVEN

        // WHEN
        val scenario = titleMapper.toDomain(ScenarioDtoMockFactory.titleDto)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.titleModel,
        )
    }

    @Test
    fun `WHEN map null THEN return TitleModel with null value`() {
        // GIVEN

        // WHEN
        val scenario = titleMapper.toDomain(null)

        // THEN
        assertEquals(
            actual = scenario,
            expected = TitleModel(),
        )
    }
}
