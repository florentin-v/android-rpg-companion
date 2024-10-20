package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.dto

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

internal class ScenarioDtoMapper {

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

    private fun setupScenarioElements() {
        scenarioElementDtoMap[TITLE] = title?.let(::TitleDto)
        scenarioElementDtoMap[AUTHOR] = author?.let(::AuthorDto)
        scenarioElementDtoMap[INFORMATION] = InformationDto(
            nbPlayers = nbPlayers,
            genreList = genreList,
            themeList = themeList,
        )
        scenarioElementDtoMap[SUMMARY] = summaryDescriptionParagraphList.takeIf { it.isNotEmpty() }?.let(::SummaryDto)
        scenarioElementDtoMap[CHARACTER_LIST] = characterList.takeIf { it.isNotEmpty() }?.let(::CharacterListDto)
        scenarioElementDtoMap[PLACE_LIST] = placeList.takeIf { it.isNotEmpty() }?.let(::PlaceListDto)
        scenarioElementDtoMap[CHAPTER_LIST] = chapterList.takeIf { it.isNotEmpty() }?.let(::ChapterListDto)
    }

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

    private fun addNewScenarioElement(paragraphText: String) {
        scenarioElementDtoMap[paragraphText.uppercase()] = null
    }

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

    private fun getInformationElement(text: String) {
        val (label, values) = text.split(":").map { it.trim() }
        val list = values.split("/").map { it.trim() }
        when (label.uppercase()) {
            NB_PLAYERS -> nbPlayers = values.toInt()
            GENRE_LIST -> genreList = list
            THEME_LIST -> themeList = list
        }
    }
}
