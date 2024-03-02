package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.Mockito

class PlacesModelMapperTest : BasicKoinTest() {

    private val mockPlaceModelMapper by inject<PlaceModelMapper>()
    private lateinit var placesModelMapper: PlacesModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { Mockito.mock<PlaceModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        placesModelMapper = PlacesModelMapperImpl()
    }

    @Test
    fun `GIVEN mock PlaceModelMapper WHEN map PlacesEntity THEN return PlacesModel`() {
        // GIVEN
        Mockito.`when`(mockPlaceModelMapper.to(ScenarioDtoMockFactory.placeDto1))
            .thenReturn(ScenarioModelMockFactory.placeModel1)
        Mockito.`when`(mockPlaceModelMapper.to(ScenarioDtoMockFactory.placeDto2))
            .thenReturn(ScenarioModelMockFactory.placeModel2)
        // WHEN
        val scenario = placesModelMapper.to(ScenarioDtoMockFactory.placesDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.placesModel)
    }
}
