package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceListModel
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PlacesModelMapperTest : BasicKoinTest() {

    private val mockPlaceModelMapper by inject<PlaceModelMapper>()
    private lateinit var placeListModelMapper: PlaceListModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<PlaceModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        placeListModelMapper = PlaceListModelMapper(
            placeModelMapper = mockPlaceModelMapper
        )
    }

    @Test
    fun `GIVEN mock PlaceModelMapper WHEN map PlacesDto THEN return PlacesModel`() {
        // GIVEN
        whenever(mockPlaceModelMapper.toDomain(ScenarioDtoMockFactory.placeDto1))
            .thenReturn(ScenarioModelMockFactory.placeModel1)
        whenever(mockPlaceModelMapper.toDomain(ScenarioDtoMockFactory.placeDto2))
            .thenReturn(ScenarioModelMockFactory.placeModel2)
        // WHEN
        val scenario = placeListModelMapper.toDomain(ScenarioDtoMockFactory.placeListDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.placeListModel)
    }

    @Test
    fun `WHEN map null THEN return empty PlacesModel`() {
        // GIVEN

        // WHEN
        val scenario = placeListModelMapper.toDomain(null)

        // THEN
        assertEquals(scenario, PlaceListModel())
    }
}
