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

class CharacterModelMapperTest : BasicKoinTest() {

    private val mockDescriptionModeMapper by inject<DescriptionModelMapper>()
    private lateinit var characterModelMapper: CharacterModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DescriptionModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        characterModelMapper = CharacterModelMapper(
            descriptionModelMapper = mockDescriptionModeMapper
        )
    }

    @Test
    fun `GIVEN mock DescriptionModelMapper WHEN map CharacterDto THEN return CharacterModel`() {
        // GIVEN
        whenever(mockDescriptionModeMapper.toDomain(ScenarioDtoMockFactory.characterDescriptionDto1))
            .thenReturn(ScenarioModelMockFactory.characterDescriptionModel1)

        // WHEN
        val scenario = characterModelMapper.toDomain(ScenarioDtoMockFactory.characterDto1)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.characterModel1)
    }
}
