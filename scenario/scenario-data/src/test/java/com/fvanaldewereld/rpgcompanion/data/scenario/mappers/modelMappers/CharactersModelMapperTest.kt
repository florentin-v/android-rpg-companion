package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharactersModel
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

class CharactersModelMapperTest : BasicKoinTest() {

    private val mockCharacterModelMapper by inject<CharacterModelMapper>()
    private lateinit var charactersModelMapper: CharactersModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<CharacterModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        charactersModelMapper = CharactersModelMapperImpl()
    }

    @Test
    fun `GIVEN mock CharacterModelMapper WHEN map CharactersDto THEN return CharactersModel`() {
        // GIVEN
        whenever(mockCharacterModelMapper.to(ScenarioDtoMockFactory.characterDto1))
            .thenReturn(ScenarioModelMockFactory.characterModel1)
        whenever(mockCharacterModelMapper.to(ScenarioDtoMockFactory.characterDto2))
            .thenReturn(ScenarioModelMockFactory.characterModel2)
        // WHEN
        val scenario = charactersModelMapper.to(ScenarioDtoMockFactory.charactersDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.charactersModel)
    }

    @Test
    fun `WHEN map null THEN return empty CharactersModel`() {
        // GIVEN

        // WHEN
        val scenario = charactersModelMapper.to(null)

        // THEN
        assertEquals(scenario, CharactersModel())
    }
}
