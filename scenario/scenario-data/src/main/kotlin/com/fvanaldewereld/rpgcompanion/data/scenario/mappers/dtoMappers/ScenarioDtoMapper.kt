package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dtoMappers

import com.fvanaldewereld.rpgcompanion.data.scenario.dto.AuthorDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ChapterDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ChaptersDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.CharacterDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.CharactersDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.DescriptionDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.InformationDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.PlaceDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.PlacesDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.ScenarioElementDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.SummaryDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.TextType
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.TitleDto
import com.fvanaldewereld.rpgcompanion.data.scenario.extensions.getText
import com.google.api.services.docs.v1.model.Document

private const val TITLE = "TITLE"
private const val AUTHOR = "AUTHOR"
private const val INFORMATION = "INFORMATION"
private const val SUMMARY = "SUMMARY"
private const val CHARACTERS = "CHARACTERS"
private const val PLACES = "PLACES"
private const val CHAPTERS = "CHAPTERS"
private const val NUMBER_OF_PLAYERS = "NUMBER OF PLAYERS"
private const val GENRES = "GENRES"
private const val THEMES = "THEMES"

interface ScenarioDtoMapper : DtoMapper<Document, ScenarioDto>

internal class ScenarioDtoMapperImpl : ScenarioDtoMapper {

    private val scenarioElements = mutableMapOf<String, ScenarioElementDto?>()

    private var title: String? = null
    private var author: String? = null
    private var nbPlayers = 1
    private var genres = emptyList<String>()
    private var themes = emptyList<String>()

    private val summaryDescriptionParagraphs = mutableListOf<String>()

    private val characters = mutableListOf<CharacterDto>()
    private var lastCharacterName: String? = null
    private val lastCharacterDescriptionParagraphs = mutableListOf<String>()

    private val places = mutableListOf<PlaceDto>()
    private var lastPlaceName: String? = null
    private val lastPlaceDescriptionParagraphs = mutableListOf<String>()

    private val chapters = mutableListOf<ChapterDto>()
    private var lastChapterName: String? = null
    private val lastChapterDescriptionParagraphs = mutableListOf<String>()

    override fun to(from: Document): ScenarioDto {
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
            author = scenarioElements[AUTHOR] as? AuthorDto,
            characters = scenarioElements[CHARACTERS] as? CharactersDto,
            chapters = scenarioElements[CHAPTERS] as? ChaptersDto,
            documentName = from.title,
            information = scenarioElements[INFORMATION] as? InformationDto,
            places = scenarioElements[PLACES] as? PlacesDto,
            summary = scenarioElements[SUMMARY] as? SummaryDto,
            title = scenarioElements[TITLE] as? TitleDto,
        )
    }

    private fun setupScenarioElements() {
        scenarioElements[TITLE] = title?.let { TitleDto(value = it) }
        scenarioElements[AUTHOR] = author?.let { AuthorDto(name = it) }
        scenarioElements[INFORMATION] = InformationDto(
            nbPlayers = nbPlayers,
            genres = genres,
            themes = themes,
        )
        scenarioElements[SUMMARY] = if (summaryDescriptionParagraphs.isNotEmpty()) {
            SummaryDto(
                text = DescriptionDto(summaryDescriptionParagraphs),
            )
        } else {
            null
        }
        scenarioElements[CHARACTERS] = if (characters.isNotEmpty()) {
            CharactersDto(characters = characters)
        } else {
            null
        }
        scenarioElements[PLACES] = if (places.isNotEmpty()) {
            PlacesDto(
                places = places,
            )
        } else {
            null
        }
        scenarioElements[CHAPTERS] = if (chapters.isNotEmpty()) {
            ChaptersDto(
                chapters = chapters,
            )
        } else {
            null
        }
    }

    private fun updateLastScenarioSubElementDescription(paragraphText: String) {
        val lastKey = scenarioElements.keys.lastOrNull()
        when (lastKey) {
            INFORMATION -> {
                getInformationElement(paragraphText)
            }

            CHARACTERS -> {
                lastCharacterDescriptionParagraphs.add(paragraphText)
            }

            PLACES -> {
                lastPlaceDescriptionParagraphs.add(paragraphText)
            }

            CHAPTERS -> {
                lastChapterDescriptionParagraphs.add(paragraphText)
            }

            SUMMARY -> {
                summaryDescriptionParagraphs.add(paragraphText)
            }

            else -> {
                // DO NOTHING
            }
        }
    }

    private fun updateLastScenarioSubElementName(paragraphText: String) {
        val lastKey = scenarioElements.keys.lastOrNull()
        when (lastKey) {
            CHARACTERS -> {
                addLastCharacter()
                lastCharacterName = paragraphText
            }

            PLACES -> {
                addLastPlace()
                lastPlaceName = paragraphText
            }

            CHAPTERS -> {
                addLastChapter()
                lastChapterName = paragraphText
            }

            else -> {
                // DO NOTHING
            }
        }
    }

    private fun addNewScenarioElement(paragraphText: String) {
        when (paragraphText.uppercase()) {
            INFORMATION -> {
                scenarioElements[INFORMATION] = null
            }

            SUMMARY -> {
                scenarioElements[SUMMARY] = null
            }

            CHARACTERS -> {
                scenarioElements[CHARACTERS] = null
            }

            PLACES -> {
                scenarioElements[PLACES] = null
            }

            CHAPTERS -> {
                scenarioElements[CHAPTERS] = null
            }
        }
    }

    private fun addLastItemForLastKey() {
        val lastKey = scenarioElements.keys.lastOrNull()
        when (lastKey) {
            CHARACTERS -> {
                addLastCharacter()
            }

            PLACES -> {
                addLastPlace()
            }

            CHAPTERS -> {
                addLastChapter()
            }

            else -> {
                // DO NOTHING
            }
        }
    }

    private fun addLastChapter() {
        if (lastChapterName != null) {
            chapters.add(
                ChapterDto(
                    name = lastChapterName.toString(),
                    description = DescriptionDto(paragraphs = lastChapterDescriptionParagraphs.toList()),
                ),
            )
            lastChapterName = null
            lastChapterDescriptionParagraphs.clear()
        }
    }

    private fun addLastPlace() {
        if (lastPlaceName != null) {
            places.add(
                PlaceDto(
                    name = lastPlaceName.toString(),
                    description = DescriptionDto(paragraphs = lastPlaceDescriptionParagraphs.toList()),
                ),
            )
            lastPlaceName = null
            lastPlaceDescriptionParagraphs.clear()
        }
    }

    private fun addLastCharacter() {
        if (lastCharacterName != null) {
            characters.add(
                CharacterDto(
                    name = lastCharacterName.toString(),
                    description = DescriptionDto(paragraphs = lastCharacterDescriptionParagraphs.toList()),
                ),
            )
            lastCharacterName = null
            lastCharacterDescriptionParagraphs.clear()
        }
    }

    private fun getInformationElement(text: String) {
        val stringList = text.split(":")
        val label = stringList[0].trim()
        val values = stringList[1].trim()
        when (label.uppercase()) {
            NUMBER_OF_PLAYERS -> nbPlayers = values.toInt()
            GENRES -> genres = values.split("/").map { it.trim() }
            THEMES -> themes = values.split("/").map { it.trim() }
        }
    }
}
