package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterListModel
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

class ChaptersModelMapperTest : BasicKoinTest() {

    private val mockChapterModelMapper by inject<ChapterModelMapper>()
    private lateinit var chapterListModelMapper: ChapterListModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<ChapterModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        chapterListModelMapper = ChapterListModelMapper(
            chapterModelMapper = mockChapterModelMapper
        )
    }

    @Test
    fun `GIVEN mock ChapterModelMapper WHEN map ChaptersDto THEN return ChaptersModel`() {
        // GIVEN
        whenever(mockChapterModelMapper.toDomain(ScenarioDtoMockFactory.chapterDto))
            .thenReturn(ScenarioModelMockFactory.chapterModel)
        // WHEN
        val scenario = chapterListModelMapper.toDomain(ScenarioDtoMockFactory.chapterListDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.chapterListModel)
    }

    @Test
    fun `WHEN map null THEN return empty ChaptersModel`() {
        // GIVEN

        // WHEN
        val scenario = chapterListModelMapper.toDomain(null)

        // THEN
        assertEquals(scenario, ChapterListModel())
    }
}
