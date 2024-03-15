package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

import BasicKoinTest
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChaptersModel
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioDtoMockFactory
import com.fvanaldewereld.rpgcompanion.mockFactory.ScenarioModelMockFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.Mockito

class ChaptersModelMapperTest : BasicKoinTest() {

    private val mockChapterModelMapper by inject<ChapterModelMapper>()
    private lateinit var chaptersModelMapper: ChaptersModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { Mockito.mock<ChapterModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        chaptersModelMapper = ChaptersModelMapperImpl()
    }

    @Test
    fun `GIVEN mock ChapterModelMapper WHEN map ChaptersDto THEN return ChaptersModel`() {
        // GIVEN
        Mockito.`when`(mockChapterModelMapper.to(ScenarioDtoMockFactory.chapterDto))
            .thenReturn(ScenarioModelMockFactory.chapterModel)
        // WHEN
        val scenario = chaptersModelMapper.to(ScenarioDtoMockFactory.chaptersDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.chaptersModel)
    }

    @Test
    fun `WHEN map null THEN return empty ChaptersModel`() {
        // GIVEN

        // WHEN
        val scenario = chaptersModelMapper.to(null)

        // THEN
        assertEquals(scenario, ChaptersModel())
    }
}
