package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import BasicKoinTest
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

class PlaceModelMapperTest : BasicKoinTest() {

    private val mockDescriptionModeMapper by inject<DescriptionModelMapper>()
    private lateinit var placeModelMapper: PlaceModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DescriptionModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        placeModelMapper = PlaceModelMapper(
            descriptionModelMapper = mockDescriptionModeMapper,
        )
    }

    @Test
    fun `GIVEN mock DescriptionModelMapper WHEN map PlaceDto THEN return PlaceModel`() {
        // GIVEN
        whenever(mockDescriptionModeMapper.toDomain(ScenarioDtoMockFactory.placeDescriptionDto1))
            .thenReturn(ScenarioModelMockFactory.placeDescriptionModel1)

        // WHEN
        val scenario = placeModelMapper.toDomain(ScenarioDtoMockFactory.placeDto1)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.placeModel1)
    }
}
