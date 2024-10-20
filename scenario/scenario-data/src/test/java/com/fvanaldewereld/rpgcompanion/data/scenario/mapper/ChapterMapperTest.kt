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

class ChapterMapperTest : BasicKoinTest() {

    private val mockDescriptionMapper by inject<DescriptionMapper>()
    private lateinit var chapterMapper: ChapterMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DescriptionMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        chapterMapper = ChapterMapper(
            descriptionMapper = mockDescriptionMapper,
        )
    }

    @Test
    fun `GIVEN mock DescriptionMapper WHEN map ChapterDto THEN return ChapterModel`() {
        // GIVEN
        whenever(mockDescriptionMapper.toDomain(ScenarioDtoMockFactory.chapterDescriptionDto))
            .thenReturn(ScenarioModelMockFactory.chapterDescriptionModel)

        // WHEN
        val scenario = chapterMapper.toDomain(ScenarioDtoMockFactory.chapterDto)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.chapterModel,
        )
    }

    @Test
    fun `WHEN map ChapterLocalDb THEN return ChapterModel`() {
        // GIVEN

        // WHEN
        val scenario = chapterMapper.toDomain(ScenarioDbObjectMockFactory.chapterLocalDb)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.chapterModel,
        )
    }

    @Test
    fun `WHEN map ChapterModel THEN return ChapterLocalDb`() {
        // GIVEN

        // WHEN
        val scenario = chapterMapper.toDataLocalDb(ScenarioModelMockFactory.chapterModel)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioDbObjectMockFactory.chapterLocalDb,
        )
    }
}
