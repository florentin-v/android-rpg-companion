package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers

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

class ChapterModelMapperTest : BasicKoinTest() {

    private val mockDescriptionModeMapper by inject<DescriptionModelMapper>()
    private lateinit var chapterModelMapper: ChapterModelMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DescriptionModelMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        chapterModelMapper = ChapterModelMapperImpl()
    }

    @Test
    fun `GIVEN mock DescriptionModelMapper WHEN map ChapterDto THEN return ChapterModel`() {
        // GIVEN
        whenever(mockDescriptionModeMapper.to(ScenarioDtoMockFactory.chapterDescriptionDto))
            .thenReturn(ScenarioModelMockFactory.chapterDescriptionModel)

        // WHEN
        val scenario = chapterModelMapper.to(ScenarioDtoMockFactory.chapterDto)

        // THEN
        assertEquals(scenario, ScenarioModelMockFactory.chapterModel)
    }
}
