package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.test.common.BasicKoinTest
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDbObjectMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class PlaceMapperTest : BasicKoinTest() {

    private val mockDescriptionMapper by inject<DescriptionMapper>()

    private lateinit var placeMapper: PlaceMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DescriptionMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        placeMapper = PlaceMapper(
            descriptionMapper = mockDescriptionMapper,
        )
    }

    @Test
    fun `GIVEN mock DescriptionMapper WHEN map PlaceDto THEN return PlaceModel`() {
        // GIVEN
        whenever(mockDescriptionMapper.toDomain(ScenarioDtoMockFactory.placeDescriptionDto1))
            .thenReturn(ScenarioModelMockFactory.placeDescriptionModel1)

        // WHEN
        val scenario = placeMapper.toDomain(ScenarioDtoMockFactory.placeDto1)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.placeModel1,
        )
    }

    @Test
    fun `WHEN map PlaceLocalDb THEN return PlaceModel`() {
        // GIVEN

        // WHEN
        val scenario = placeMapper.toDomain(ScenarioDbObjectMockFactory.placeLocalDb1)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.placeModel1,
        )
    }

    @Test
    fun `WHEN map PlaceModel THEN return PlaceLocalDb`() {
        // GIVEN

        // WHEN
        val scenario = placeMapper.toDataLocalDb(ScenarioModelMockFactory.placeModel1)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioDbObjectMockFactory.placeLocalDb1,
        )
    }
}
