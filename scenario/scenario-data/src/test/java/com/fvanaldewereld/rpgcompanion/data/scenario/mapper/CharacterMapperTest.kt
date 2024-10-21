package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDbObjectMockFactory
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

class CharacterMapperTest : BasicKoinTest() {

    private val mockDescriptionMapper by inject<DescriptionMapper>()

    private lateinit var characterMapper: CharacterMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DescriptionMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        characterMapper = CharacterMapper(
            descriptionMapper = mockDescriptionMapper,
        )
    }


    @Test
    fun `WHEN map CharacterDto THEN return CharacterModel`() {
        // GIVEN
        whenever(mockDescriptionMapper.toDomain(ScenarioDtoMockFactory.characterDescriptionDto1))
            .thenReturn(ScenarioModelMockFactory.characterDescriptionModel1)

        // WHEN
        val scenario = characterMapper.toDomain(ScenarioDtoMockFactory.characterDto1)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.characterModel1,
        )
    }

    @Test
    fun `WHEN map CharacterLocalDb THEN return CharacterModel`() {
        // GIVEN

        // WHEN
        val scenario = characterMapper.toDomain(ScenarioDbObjectMockFactory.characterLocalDb1)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.characterModel1,
        )
    }

    @Test
    fun `WHEN map CharacterModel THEN return CharacterLocalDb`() {
        // GIVEN

        // WHEN
        val scenario = characterMapper.toDataLocalDb(ScenarioModelMockFactory.characterModel1)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioDbObjectMockFactory.characterLocalDb1,
        )
    }
}
