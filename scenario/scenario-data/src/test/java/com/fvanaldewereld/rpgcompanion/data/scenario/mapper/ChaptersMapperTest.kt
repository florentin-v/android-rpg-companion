package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterListModel
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

class ChaptersMapperTest : BasicKoinTest() {

    private val mockChapterMapper by inject<ChapterMapper>()

    private lateinit var chapterListMapper: ChapterListMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<ChapterMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        chapterListMapper = ChapterListMapper(
            chapterMapper = mockChapterMapper,
        )
    }

    @Test
    fun `GIVEN mock ChapterMapper WHEN map ChaptersDto THEN return ChaptersModel`() {
        // GIVEN
        whenever(mockChapterMapper.toDomain(ScenarioDtoMockFactory.chapterDto))
            .thenReturn(ScenarioModelMockFactory.chapterModel)
        // WHEN
        val scenario = chapterListMapper.toDomain(ScenarioDtoMockFactory.chapterListDto)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.chapterListModel,
        )
    }

    @Test
    fun `WHEN map null THEN return empty ChaptersModel`() {
        // GIVEN

        // WHEN
        val scenario = chapterListMapper.toDomain(null)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ChapterListModel(),
        )
    }
}
