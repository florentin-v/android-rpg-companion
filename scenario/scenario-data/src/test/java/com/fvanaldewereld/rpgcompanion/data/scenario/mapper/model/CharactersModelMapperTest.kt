package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterListModel
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
    private lateinit var characterListModelMapper: CharacterListModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<CharacterModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        characterListModelMapper = CharacterListModelMapper(
            characterModelMapper = mockCharacterModelMapper
        )
    }

    @Test
    fun `GIVEN mock CharacterModelMapper WHEN map CharactersDto THEN return CharactersModel`() {
        // GIVEN
        whenever(mockCharacterModelMapper.toDomain(ScenarioDtoMockFactory.characterDto1))
            .thenReturn(ScenarioModelMockFactory.characterModel1)
        whenever(mockCharacterModelMapper.toDomain(ScenarioDtoMockFactory.characterDto2))
            .thenReturn(ScenarioModelMockFactory.characterModel2)
        // WHEN
        val scenario = characterListModelMapper.toDomain(ScenarioDtoMockFactory.characterListDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.characterListModel)
    }

    @Test
    fun `WHEN map null THEN return empty CharactersModel`() {
        // GIVEN

        // WHEN
        val scenario = characterListModelMapper.toDomain(null)

        // THEN
        assertEquals(scenario, CharacterListModel())
    }
}
