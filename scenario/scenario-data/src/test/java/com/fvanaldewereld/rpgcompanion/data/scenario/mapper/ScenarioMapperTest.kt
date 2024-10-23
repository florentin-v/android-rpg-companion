package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.InformationModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.MainInfoModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceListModel
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.embedded.InformationLocalDb
import com.fvanaldewereld.rpgcompanion.mockFactory.GoogleDocsMockFactory
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
import kotlin.test.assertNotNull

class ScenarioMapperTest : BasicKoinTest() {

    private val mockChapterListMapper by inject<ChapterListMapper>()
    private val mockChapterMapper by inject<ChapterMapper>()
    private val mockCharacterListMapper by inject<CharacterListMapper>()
    private val mockCharacterMapper by inject<CharacterMapper>()
    private val mockInformationMapper by inject<InformationMapper>()
    private val mockMainInfoMapper by inject<MainInfoMapper>()
    private val mockPlaceListMapper by inject<PlaceListMapper>()
    private val mockPlaceMapper by inject<PlaceMapper>()

    private lateinit var scenarioMapper: ScenarioMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<ChapterListMapper>() }
                single { mock<ChapterMapper>() }
                single { mock<CharacterListMapper>() }
                single { mock<CharacterMapper>() }
                single { mock<InformationMapper>() }
                single { mock<MainInfoMapper>() }
                single { mock<PlaceListMapper>() }
                single { mock<PlaceMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        scenarioMapper = ScenarioMapper(
            chapterListMapper = mockChapterListMapper,
            chapterMapper = mockChapterMapper,
            characterListMapper = mockCharacterListMapper,
            characterMapper = mockCharacterMapper,
            informationMapper = mockInformationMapper,
            mainInfoMapper = mockMainInfoMapper,
            placeListMapper = mockPlaceListMapper,
            placeMapper = mockPlaceMapper,
        )

        assertNotNull(mockChapterMapper)
        assertNotNull(mockCharacterMapper)
        assertNotNull(mockPlaceMapper)
        assertNotNull(mockInformationMapper)
    }

    @Test
    fun `WHEN map Scenario THEN return ScenarioModel`() {
        // GIVEN
        whenever(mockChapterMapper.toDomain(ScenarioDbObjectMockFactory.chapterLocalDb))
            .thenReturn(ScenarioModelMockFactory.chapterModel)
        whenever(mockCharacterMapper.toDomain(ScenarioDbObjectMockFactory.characterLocalDb1))
            .thenReturn(ScenarioModelMockFactory.characterModel1)
        whenever(mockCharacterMapper.toDomain(ScenarioDbObjectMockFactory.characterLocalDb2))
            .thenReturn(ScenarioModelMockFactory.characterModel2)
        whenever(mockPlaceMapper.toDomain(ScenarioDbObjectMockFactory.placeLocalDb1))
            .thenReturn(ScenarioModelMockFactory.placeModel1)
        whenever(mockPlaceMapper.toDomain(ScenarioDbObjectMockFactory.placeLocalDb2))
            .thenReturn(ScenarioModelMockFactory.placeModel2)
        whenever(mockInformationMapper.toDomain(ScenarioDbObjectMockFactory.informationLocalDb))
            .thenReturn(ScenarioModelMockFactory.informationModel)

        // WHEN
        val scenarioModel = scenarioMapper.toDomain(ScenarioDbObjectMockFactory.scenarioLocalDb)

        // THEN
        assertEquals(
            actual = scenarioModel,
            expected = ScenarioModelMockFactory.scenarioModelWithId,
        )
    }

    @Test
    fun `WHEN map ScenarioModel THEN return Scenario`() {
        // GIVEN
        whenever(mockChapterMapper.toDataLocalDb(ScenarioModelMockFactory.chapterModel))
            .thenReturn(ScenarioDbObjectMockFactory.chapterLocalDb)
        whenever(mockCharacterMapper.toDataLocalDb(ScenarioModelMockFactory.characterModel1))
            .thenReturn(ScenarioDbObjectMockFactory.characterLocalDb1)
        whenever(mockCharacterMapper.toDataLocalDb(ScenarioModelMockFactory.characterModel2))
            .thenReturn(ScenarioDbObjectMockFactory.characterLocalDb2)
        whenever(mockPlaceMapper.toDataLocalDb(ScenarioModelMockFactory.placeModel1))
            .thenReturn(ScenarioDbObjectMockFactory.placeLocalDb1)
        whenever(mockPlaceMapper.toDataLocalDb(ScenarioModelMockFactory.placeModel2))
            .thenReturn(ScenarioDbObjectMockFactory.placeLocalDb2)
        whenever(mockInformationMapper.toDataLocalDb(ScenarioModelMockFactory.informationModel))
            .thenReturn(ScenarioDbObjectMockFactory.informationLocalDb)

        // WHEN
        val scenarioModel = scenarioMapper.toDataLocalDb(ScenarioModelMockFactory.scenarioModelWithId)

        // THEN
        assertEquals(
            actual = scenarioModel,
            expected = ScenarioDbObjectMockFactory.scenarioLocalDb,
        )
    }

    @Test
    fun `WHEN map ScenarioModel without Id THEN return Scenario`() {
        // GIVEN
        whenever(mockChapterMapper.toDataLocalDb(ScenarioModelMockFactory.chapterModel))
            .thenReturn(ScenarioDbObjectMockFactory.chapterLocalDb)
        whenever(mockCharacterMapper.toDataLocalDb(ScenarioModelMockFactory.characterModel1))
            .thenReturn(ScenarioDbObjectMockFactory.characterLocalDb1)
        whenever(mockCharacterMapper.toDataLocalDb(ScenarioModelMockFactory.characterModel2))
            .thenReturn(ScenarioDbObjectMockFactory.characterLocalDb2)
        whenever(mockPlaceMapper.toDataLocalDb(ScenarioModelMockFactory.placeModel1))
            .thenReturn(ScenarioDbObjectMockFactory.placeLocalDb1)
        whenever(mockPlaceMapper.toDataLocalDb(ScenarioModelMockFactory.placeModel2))
            .thenReturn(ScenarioDbObjectMockFactory.placeLocalDb2)
        whenever(mockInformationMapper.toDataLocalDb(ScenarioModelMockFactory.informationModel))
            .thenReturn(ScenarioDbObjectMockFactory.informationLocalDb)

        // WHEN
        val scenarioModel = scenarioMapper.toDataLocalDb(ScenarioModelMockFactory.scenarioModelWithoutId)

        // THEN
        assertEquals(
            actual = scenarioModel,
            expected = ScenarioDbObjectMockFactory.scenarioLocalDb,
        )
    }

    @Test
    fun `WHEN map empty Scenario THEN return empty ScenarioModel`() {
        // GIVEN
        whenever(mockInformationMapper.toDomain(InformationLocalDb()))
            .thenReturn(InformationModel())

        // WHEN
        val scenarioModel = scenarioMapper.toDomain(ScenarioDbObjectMockFactory.emptyScenarioLocalDb)

        // THEN
        assertEquals(
            actual = scenarioModel,
            expected = ScenarioModelMockFactory.emptyScenarioModelWithId,
        )
    }

    @Test
    fun `WHEN map empty ScenarioModel THEN return empty Scenario`() {
        // GIVEN
        whenever(mockInformationMapper.toDataLocalDb(InformationModel()))
            .thenReturn(InformationLocalDb())

        // WHEN
        val scenarioModel = scenarioMapper.toDataLocalDb(ScenarioModelMockFactory.emptyScenarioModelWithId)

        // THEN
        assertEquals(
            actual = scenarioModel,
            expected = ScenarioDbObjectMockFactory.emptyScenarioLocalDb,
        )
    }

    @Test
    fun `WHEN map empty Document THEN return empty ScenarioDto`() {
        // WHEN
        val scenario = scenarioMapper.toDataDto(GoogleDocsMockFactory.emptyGoogleDocsDocument)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioDtoMockFactory.emptyScenarioDto,
        )
    }

    @Test
    fun `WHEN map Document THEN return ScenarioDto`() {
        // WHEN
        val scenario = scenarioMapper.toDataDto(GoogleDocsMockFactory.googleDocsDocument)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioDtoMockFactory.scenarioDto,
        )
    }

    @Test
    fun `WHEN map empty ScenarioDto THEN return empty ScenarioModel`() {
        // GIVEN
        whenever(mockChapterListMapper.toDomain(null))
            .thenReturn(ChapterListModel())
        whenever(mockCharacterListMapper.toDomain(null))
            .thenReturn(CharacterListModel())
        whenever(mockMainInfoMapper.toDomain(ScenarioDtoMockFactory.emptyScenarioDto))
            .thenReturn(MainInfoModel())
        whenever(mockPlaceListMapper.toDomain(null))
            .thenReturn(PlaceListModel())

        // WHEN
        val scenario = scenarioMapper.toDomain(ScenarioDtoMockFactory.emptyScenarioDto)

        // THEN
        assertEquals(
            actual = scenario,
            expected = ScenarioModelMockFactory.emptyScenarioModelWithoutId,
        )
    }

    @Test
    fun `WHEN map ScenarioDto THEN return ScenarioModel`() {
        // GIVEN
        whenever(mockChapterListMapper.toDomain(ScenarioDtoMockFactory.chapterListDto))
            .thenReturn(ScenarioModelMockFactory.chapterListModel)
        whenever(mockCharacterListMapper.toDomain(ScenarioDtoMockFactory.characterListDto))
            .thenReturn(ScenarioModelMockFactory.characterListModel)
        whenever(mockInformationMapper.toDomain(ScenarioDtoMockFactory.informationDto))
            .thenReturn(ScenarioModelMockFactory.informationModel)
        whenever(mockPlaceListMapper.toDomain(ScenarioDtoMockFactory.placeListDto))
            .thenReturn(ScenarioModelMockFactory.placeListModel)
        whenever(mockMainInfoMapper.toDomain(ScenarioDtoMockFactory.scenarioDto))
            .thenReturn(ScenarioModelMockFactory.mainInfoModel)

        // WHEN
        val scenario = scenarioMapper.toDomain(
            ScenarioDtoMockFactory.scenarioDto,
        )

        // THEN
        assertEquals(
            ScenarioModelMockFactory.scenarioModelWithoutId,
            scenario,
        )
    }
}
