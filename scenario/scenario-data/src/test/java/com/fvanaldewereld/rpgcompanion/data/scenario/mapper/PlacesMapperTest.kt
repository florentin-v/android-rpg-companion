package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceListModel
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import com.fvanaldewereld.rpgcompanion.test.common.BasicKoinTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class PlacesMapperTest : BasicKoinTest() {

    private val mockPlaceMapper by inject<PlaceMapper>()

    private lateinit var placeListMapper: PlaceListMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<PlaceMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        placeListMapper = PlaceListMapper(
            placeMapper = mockPlaceMapper,
        )
    }

    @Test
    fun `GIVEN mock PlaceMapper WHEN map PlacesDto THEN return PlacesModel`() {
        // GIVEN
        whenever(mockPlaceMapper.toDomain(ScenarioDtoMockFactory.placeDto1))
            .thenReturn(ScenarioModelMockFactory.placeModel1)
        whenever(mockPlaceMapper.toDomain(ScenarioDtoMockFactory.placeDto2))
            .thenReturn(ScenarioModelMockFactory.placeModel2)
        // WHEN
        val scenario = placeListMapper.toDomain(ScenarioDtoMockFactory.placeListDto)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.placeListModel,
        )
    }

    @Test
    fun `WHEN map null THEN return empty PlacesModel`() {
        // GIVEN

        // WHEN
        val scenario = placeListMapper.toDomain(null)

        // THEN
        assertEquals(
            actual = scenario,
            expected = PlaceListModel(),
        )
    }
}
