package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.AuthorModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.MainInfoModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.SummaryModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.AuthorDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ChapterDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ChapterListDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.CharacterDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.CharacterListDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.DescriptionDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.InformationDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.PlaceDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.PlaceListDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioElementDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.SummaryDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.TextType
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.TitleDto
import com.fvanaldewereld.rpgcompanion.data.scenario.extension.getText
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.ScenarioBaseLocalDb
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.relation.ScenarioLocalDb
import com.google.api.services.docs.v1.model.Document

private const val TITLE = "TITLE"
private const val AUTHOR = "AUTHOR"
private const val INFORMATION = "INFORMATION"
private const val SUMMARY = "SUMMARY"
private const val CHARACTER_LIST = "CHARACTERS"
private const val PLACE_LIST = "PLACES"
private const val CHAPTER_LIST = "CHAPTERS"
private const val NB_PLAYERS = "NUMBER OF PLAYERS"
private const val GENRE_LIST = "GENRES"
private const val THEME_LIST = "THEMES"

/**
 * Mapper for converting between different data models related to a scenario.
 *
 * @param chapterListMapper Mapper for chapter list related data models.
 * @param chapterMapper Mapper for chapter related data models.
 * @param characterListMapper Mapper for character list related data models.
 * @param characterMapper Mapper for character related data models.
 * @param informationMapper Mapper for information related data models.
 * @param mainInfoMapper Mapper for main information related data models.
 * @param placeListMapper Mapper for place list related data models.
 * @param placeMapper Mapper for place related data models.
 */
@Suppress("TooManyFunctions", "LongParameterList")
// TODO Rework to reduce the number of functions
// TODO Maybe fix LongParameterList with TooManyFunctions or ... find a solution
internal class ScenarioMapper(
    private val chapterListMapper: ChapterListMapper,
    private val chapterMapper: ChapterMapper,
    private val characterListMapper: CharacterListMapper,
    private val characterMapper: CharacterMapper,
    private val informationMapper: InformationMapper,
    private val mainInfoMapper: MainInfoMapper,
    private val placeListMapper: PlaceListMapper,
    private val placeMapper: PlaceMapper,
) {

    // region Properties
    private val scenarioElementDtoMap = mutableMapOf<String, ScenarioElementDto?>()

    private var title: String? = null
    private var author: String? = null
    private var nbPlayers = 1
    private var genreList = emptyList<String>()
    private var themeList = emptyList<String>()

    private val summaryDescriptionParagraphList = mutableListOf<String>()

    private val characterList = mutableListOf<CharacterDto>()
    private var lastCharacterName: String? = null
    private val lastCharacterDescriptionParagraphList = mutableListOf<String>()

    private val placeList = mutableListOf<PlaceDto>()
    private var lastPlaceName: String? = null
    private val lastPlaceDescriptionParagraphList = mutableListOf<String>()

    private val chapterList = mutableListOf<ChapterDto>()
    private var lastChapterName: String? = null
    private val lastChapterDescriptionParagraphList = mutableListOf<String>()
    // endregion

    // region Public Methods
    /**
     * Converts a Google Document to a [ScenarioDto].
     *
     * @param from The Google Document to convert.
     * @return The resulting [ScenarioDto].
     */
    fun toDataDto(from: Document): ScenarioDto {
        from.body.content.forEach { structuralElement ->
            val namedStyleType = structuralElement?.paragraph?.paragraphStyle?.namedStyleType
            val paragraphText = structuralElement.getText()
            if (paragraphText.isNotBlank()) {
                when (namedStyleType) {
                    TextType.TITLE.name -> {
                        title = paragraphText
                    }

                    TextType.SUBTITLE.name -> {
                        author = paragraphText
                    }

                    TextType.HEADING_1.name -> {
                        addLastItemForLastKey()
                        addNewScenarioElement(paragraphText)
                    }

                    TextType.HEADING_2.name -> {
                        updateLastScenarioSubElementName(paragraphText)
                    }

                    TextType.NORMAL_TEXT.name -> {
                        updateLastScenarioSubElementDescription(paragraphText)
                    }
                }
            }
        }
        addLastItemForLastKey()
        setupScenarioElements()

        return ScenarioDto(
            author = scenarioElementDtoMap[AUTHOR] as? AuthorDto,
            characterList = scenarioElementDtoMap[CHARACTER_LIST] as? CharacterListDto,
            chapterList = scenarioElementDtoMap[CHAPTER_LIST] as? ChapterListDto,
            documentName = from.title,
            information = scenarioElementDtoMap[INFORMATION] as? InformationDto,
            placeList = scenarioElementDtoMap[PLACE_LIST] as? PlaceListDto,
            summary = scenarioElementDtoMap[SUMMARY] as? SummaryDto,
            title = scenarioElementDtoMap[TITLE] as? TitleDto,
        )
    }

    /**
     * Converts a [ScenarioLocalDb] to a [ScenarioModel].
     *
     * @param from The [ScenarioLocalDb] to convert.
     * @return The resulting [ScenarioModel].
     */
    fun toDomain(from: ScenarioLocalDb) = ScenarioModel(
        id = from.scenarioBaseLocalDb.id,
        documentName = from.scenarioBaseLocalDb.documentName,
        mainInfo = MainInfoModel(
            author = AuthorModel(name = from.scenarioBaseLocalDb.author),
            information = from.scenarioBaseLocalDb.informationLocalDb.let(informationMapper::toDomain),
            summary = SummaryModel(text = DescriptionModel(from.scenarioBaseLocalDb.summary)),
            title = TitleModel(value = from.scenarioBaseLocalDb.title),
        ),
        chapterList = ChapterListModel(
            list = from.chapterLocalDbList.map(chapterMapper::toDomain),
        ),
        characterList = CharacterListModel(
            list = from.characterLocalDbList.map(characterMapper::toDomain),
        ),
        placeList = PlaceListModel(
            list = from.placeLocalDbList.map(placeMapper::toDomain),
        ),
    )

    /**
     * Converts a [ScenarioDto] to a [ScenarioModel].
     *
     * @param from The [ScenarioDto] to convert.
     * @return The resulting [ScenarioModel].
     */
    fun toDomain(from: ScenarioDto) = ScenarioModel(
        documentName = from.documentName,
        chapterList = from.chapterList.let(chapterListMapper::toDomain),
        characterList = from.characterList.let(characterListMapper::toDomain),
        mainInfo = from.let(mainInfoMapper::toDomain),
        placeList = from.placeList.let(placeListMapper::toDomain),
    )

    /**
     * Converts a [ScenarioModel] to a [ScenarioLocalDb].
     *
     * @param from The [ScenarioModel] to convert.
     * @return The resulting [ScenarioLocalDb].
     */
    fun toDataLocalDb(from: ScenarioModel): ScenarioLocalDb {
        val scenarioBaseLocalDb = from.id?.let {
            ScenarioBaseLocalDb(
                id = it,
                author = from.mainInfo.author.name,
                documentName = from.documentName,
                informationLocalDb = from.mainInfo.information.let(informationMapper::toDataLocalDb),
                summary = from.mainInfo.summary.text.paragraphs,
                title = from.mainInfo.title.value,
            )
        } ?: ScenarioBaseLocalDb(
            author = from.mainInfo.author.name,
            documentName = from.documentName,
            informationLocalDb = from.mainInfo.information.let(informationMapper::toDataLocalDb),
            summary = from.mainInfo.summary.text.paragraphs,
            title = from.mainInfo.title.value,
        )

        return ScenarioLocalDb(
            chapterLocalDbList = from.chapterList.list.map(chapterMapper::toDataLocalDb),
            characterLocalDbList = from.characterList.list.map(characterMapper::toDataLocalDb),
            placeLocalDbList = from.placeList.list.map(placeMapper::toDataLocalDb),
            scenarioBaseLocalDb = scenarioBaseLocalDb,
        )
    }
    // endregion

    // region Private Methods
    /**
     * Sets up the scenario elements.
     */
    private fun setupScenarioElements() {
        scenarioElementDtoMap[TITLE] = title?.let(::TitleDto)
        scenarioElementDtoMap[AUTHOR] = author?.let(::AuthorDto)
        scenarioElementDtoMap[INFORMATION] = InformationDto(
            nbPlayers = nbPlayers,
            genreList = genreList,
            themeList = themeList,
        )
        scenarioElementDtoMap[SUMMARY] =
            summaryDescriptionParagraphList.takeIf { it.isNotEmpty() }?.let(::SummaryDto)
        scenarioElementDtoMap[CHARACTER_LIST] =
            characterList.takeIf { it.isNotEmpty() }?.let(::CharacterListDto)
        scenarioElementDtoMap[PLACE_LIST] =
            placeList.takeIf { it.isNotEmpty() }?.let(::PlaceListDto)
        scenarioElementDtoMap[CHAPTER_LIST] =
            chapterList.takeIf { it.isNotEmpty() }?.let(::ChapterListDto)
    }

    /**
     * Updates the description of the last scenario sub-element.
     */
    private fun updateLastScenarioSubElementDescription(paragraphText: String) {
        when (scenarioElementDtoMap.keys.lastOrNull()) {
            INFORMATION -> getInformationElement(paragraphText)
            CHARACTER_LIST -> lastCharacterDescriptionParagraphList.add(paragraphText)
            PLACE_LIST -> lastPlaceDescriptionParagraphList.add(paragraphText)
            CHAPTER_LIST -> lastChapterDescriptionParagraphList.add(paragraphText)
            SUMMARY -> summaryDescriptionParagraphList.add(paragraphText)
            else -> {
                /* DO NOTHING */
            }
        }
    }

    /**
     * Updates the name of the last scenario sub-element.
     */
    private fun updateLastScenarioSubElementName(paragraphText: String) {
        val lastKey = scenarioElementDtoMap.keys.lastOrNull()
        when (lastKey) {
            CHARACTER_LIST -> {
                addLastCharacter()
                lastCharacterName = paragraphText
            }

            PLACE_LIST -> {
                addLastPlace()
                lastPlaceName = paragraphText
            }

            CHAPTER_LIST -> {
                addLastChapter()
                lastChapterName = paragraphText
            }

            else -> {
                // DO NOTHING
            }
        }
    }

    /**
     * Adds a new scenario element.
     */
    private fun addNewScenarioElement(paragraphText: String) {
        scenarioElementDtoMap[paragraphText.uppercase()] = null
    }

    /**
     * Adds the last item for the last key.
     */
    private fun addLastItemForLastKey() {
        when (scenarioElementDtoMap.keys.lastOrNull()) {
            CHARACTER_LIST -> addLastCharacter()
            PLACE_LIST -> addLastPlace()
            CHAPTER_LIST -> addLastChapter()
            else -> {
                /* DO NOTHING */
            }
        }
    }

    /**
     * Adds the last chapter.
     */
    private fun addLastChapter() {
        if (lastChapterName != null) {
            chapterList.add(
                ChapterDto(
                    name = lastChapterName.toString(),
                    description = DescriptionDto(paragraphs = lastChapterDescriptionParagraphList.toList()),
                ),
            )
            lastChapterName = null
            lastChapterDescriptionParagraphList.clear()
        }
    }

    /**
     * Adds the last place.
     */
    private fun addLastPlace() {
        if (lastPlaceName != null) {
            placeList.add(
                PlaceDto(
                    name = lastPlaceName.toString(),
                    description = DescriptionDto(paragraphs = lastPlaceDescriptionParagraphList.toList()),
                ),
            )
            lastPlaceName = null
            lastPlaceDescriptionParagraphList.clear()
        }
    }

    /**
     * Adds the last character.
     */
    private fun addLastCharacter() {
        if (lastCharacterName != null) {
            characterList.add(
                CharacterDto(
                    name = lastCharacterName.toString(),
                    description = DescriptionDto(paragraphs = lastCharacterDescriptionParagraphList.toList()),
                ),
            )
            lastCharacterName = null
            lastCharacterDescriptionParagraphList.clear()
        }
    }

    /**
     * Gets the information element.
     */
    private fun getInformationElement(text: String) {
        val (label, values) = text.split(":").map { it.trim() }
        val list = values.split("/").map { it.trim() }
        when (label.uppercase()) {
            NB_PLAYERS -> nbPlayers = values.toInt()
            GENRE_LIST -> genreList = list
            THEME_LIST -> themeList = list
        }
    }
    // endregion
}
