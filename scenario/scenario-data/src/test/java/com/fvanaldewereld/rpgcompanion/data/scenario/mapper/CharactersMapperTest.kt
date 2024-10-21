package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterListModel
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

class CharactersMapperTest : BasicKoinTest() {

    private val mockCharacterMapper by inject<CharacterMapper>()

    private lateinit var characterListMapper: CharacterListMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<CharacterMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        characterListMapper = CharacterListMapper(
            characterMapper = mockCharacterMapper,
        )
    }

    @Test
    fun `WHEN map CharactersDto THEN return CharactersModel`() {
        // GIVEN
        whenever(mockCharacterMapper.toDomain(ScenarioDtoMockFactory.characterDto1))
            .thenReturn(ScenarioModelMockFactory.characterModel1)
        whenever(mockCharacterMapper.toDomain(ScenarioDtoMockFactory.characterDto2))
            .thenReturn(ScenarioModelMockFactory.characterModel2)
        // WHEN
        val scenario = characterListMapper.toDomain(ScenarioDtoMockFactory.characterListDto)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.characterListModel,
        )
    }

    @Test
    fun `WHEN map null THEN return empty CharactersModel`() {
        // GIVEN

        // WHEN
        val scenario = characterListMapper.toDomain(null)

        // THEN
        assertEquals(
            actual = scenario,
            expected = CharacterListModel(),
        )
    }
}
