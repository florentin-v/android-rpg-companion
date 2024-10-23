@file:Suppress("kotlin:S1192")

package com.fvanaldewereld.rpgcompanion.mockFactory

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
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.SummaryDto
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.TitleDto

object ScenarioDtoMockFactory {

    private const val SCENARIO_DOCUMENT_NAME = "Scenario - Example"

    private const val SCENARIO_TITLE = "Scenario"

    private const val SCENARIO_AUTHOR = "Author"

    private const val SCENARIO_SUMMARY_TEXT =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Sed sollicitudin malesuada condimentum. " +
            "Aenean varius turpis a ornare lobortis. " +
            "Curabitur massa turpis, commodo sit amet eleifend dignissim, porttitor ut dolor."

    private const val SCENARIO_CHAPTERS_INTRODUCTION = "Introduction"

    private const val SCENARIO_CHAPTERS_INTRODUCTION_TEXT =
        "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. " +
            "Proin aliquet at eros pretium porttitor. " +
            "Vestibulum in elit dictum, faucibus lacus et, fermentum ipsum. " +
            "Phasellus in commodo metus. " +
            "Pellentesque lacinia orci et nisi ultricies, id tempus urna porta."

    private const val NUMBER_OF_PLAYERS_VALUE = 4

    private const val GENRES_HORROR = "Horror"

    private const val GENRES_DRAMA = "Drama"

    private const val THEMES_ALIEN = "Alien"

    private const val THEMES_POSSESSION = "Possession"

    private const val THEMES_PSYCOLOGICAL = "Psycological"

    private const val SCENARIO_CHARACTERS_NAME_1 = "John Doe"

    private const val SCENARIO_CHARACTERS_NAME_2 = "Jane Doe"

    private const val SCENARIO_CHARACTERS_DESCRIPTION_1 =
        "Nullam rutrum tincidunt neque. " +
            "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. " +
            "Aliquam erat volutpat. " +
            "Curabitur blandit turpis nibh, eu lacinia diam molestie ac. " +
            "Vestibulum leo est, lobortis et tortor in, tempus egestas ligula. " +
            "Mauris mollis vitae massa ac convallis. " +
            "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. " +
            "Proin aliquet at eros pretium porttitor. " +
            "Vestibulum in elit dictum, faucibus lacus et, fermentum ipsum. " +
            "Phasellus in commodo metus. " +
            "Pellentesque lacinia orci et nisi ultricies, id tempus urna porta. " +
            "Vestibulum dictum, enim euismod posuere interdum, " +
            "massa quam blandit magna, in efficitur ligula lacus at augue. " +
            "Nam hendrerit, quam ac rhoncus malesuada, diam justo accumsan ante, " +
            "at gravida nulla arcu non mauris. " +
            "Aenean volutpat faucibus laoreet. Maecenas dignissim et elit eu lobortis."

    private const val SCENARIO_CHARACTERS_DESCRIPTION_2 =
        "Sed ullamcorper leo lacus, id mattis dolor efficitur ut. " +
            "Quisque vestibulum sapien sit amet mi varius fermentum. " +
            "Nullam nec quam rhoncus, collision dui ac, luctus nisi. " +
            "Sed in mi eget enim porta vestibulum eu ut dui. " +
            "Nulla faucibus quam ac elit semper varius. " +
            "Duis luctus elit nec lorem ullamcorper, ut consequat ipsum tristique. " +
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Nulla vel tortor tincidunt nulla rutrum tincidunt ac id ligula. " +
            "Nunc id accumsan sem, sed blandit lorem. " +
            "Suspendisse mattis tellus ac nulla tincidunt condimentum. " +
            "Vestibulum commodo nec sem id vestibulum. " +
            "Donec commodo eget turpis at tincidunt. " +
            "Donec ac magna suscipit, mollis arcu sed, euismod lectus."

    private const val SCENARIO_PLACES_NAME_1 = "Place 1"

    private const val SCENARIO_PLACES_NAME_2 = "Place 2"

    private const val SCENARIO_PLACES_DESCRIPTION_1 =
        "Nullam sit amet dui non est auctor interdum eu ut velit. " +
            "Etiam id diam erat. Sed ac mauris vitae arcu vulputate volutpat. " +
            "Maecenas ut nulla vestibulum purus placerat rutrum. " +
            "Quisque tellus ex, aliquet fermentum egestas aliquam, tincidunt eget sem. " +
            "Praesent arcu felis, scelerisque vitae velit ornare, venenatis consectetur lacus. " +
            "Curabitur consequat ipsum ac dapibus tincidunt. " +
            "Pellentesque et sem mauris. " +
            "Ut aliquam nulla non magna aliquam mattis. " +
            "Aliquam a nulla bibendum, tempus velit et, vehicula elit."

    private const val SCENARIO_PLACES_DESCRIPTION_2 =
        "Phasellus commodo enim scelerisque molestie varius. " +
            "Aenean tincidunt aliquam mi a vestibulum. " +
            "Aliquam imperdiet vel velit vitae blandit. " +
            "Praesent a dignissim nunc. " +
            "Aenean quis elementum enim, a vulputate augue. " +
            "In eget aliquet elit. " +
            "Ut nulla dui, eleifend eu urna sed, venenatis fermentum tellus. " +
            "Mauris id ante ipsum. " +
            "Vestibulum ac pulvinar ante. " +
            "Morbi eget nisi erat."

    val titleDto = TitleDto(SCENARIO_TITLE)
    val authorDto = AuthorDto(SCENARIO_AUTHOR)
    val informationDto = InformationDto(
        nbPlayers = NUMBER_OF_PLAYERS_VALUE,
        genreList = listOf(GENRES_HORROR, GENRES_DRAMA),
        themeList = listOf(THEMES_ALIEN, THEMES_POSSESSION, THEMES_PSYCOLOGICAL),
    )
    val summaryDescriptionDto = DescriptionDto(
        paragraphs = listOf(SCENARIO_SUMMARY_TEXT),
    )
    val summaryDto = SummaryDto(
        text = summaryDescriptionDto,
    )
    val characterDescriptionDto1 = DescriptionDto(
        paragraphs = listOf(SCENARIO_CHARACTERS_DESCRIPTION_1),
    )
    val characterDto1 = CharacterDto(
        name = SCENARIO_CHARACTERS_NAME_1,
        description = characterDescriptionDto1,
    )
    private val characterDescriptionDto2 = DescriptionDto(
        paragraphs = listOf(SCENARIO_CHARACTERS_DESCRIPTION_2),
    )
    val characterDto2 = CharacterDto(
        name = SCENARIO_CHARACTERS_NAME_2,
        description = characterDescriptionDto2,
    )
    val characterListDto = CharacterListDto(
        list = listOf(
            characterDto1,
            characterDto2,
        ),
    )
    val placeDescriptionDto1 = DescriptionDto(
        paragraphs = listOf(SCENARIO_PLACES_DESCRIPTION_1),
    )
    val placeDto1 = PlaceDto(
        name = SCENARIO_PLACES_NAME_1,
        description = placeDescriptionDto1,
    )
    private val placeDescriptionDto2 = DescriptionDto(
        paragraphs = listOf(SCENARIO_PLACES_DESCRIPTION_2),
    )
    val placeDto2 = PlaceDto(
        name = SCENARIO_PLACES_NAME_2,
        description = placeDescriptionDto2,
    )
    val placeListDto = PlaceListDto(
        list = listOf(
            placeDto1,
            placeDto2,
        ),
    )
    val chapterDescriptionDto = DescriptionDto(
        paragraphs = listOf(SCENARIO_CHAPTERS_INTRODUCTION_TEXT),
    )
    val chapterDto = ChapterDto(
        name = SCENARIO_CHAPTERS_INTRODUCTION,
        description = chapterDescriptionDto,
    )
    val chapterListDto = ChapterListDto(
        listOf(
            chapterDto,
        ),
    )
    val scenarioDto = ScenarioDto(
        documentName = SCENARIO_DOCUMENT_NAME,
        title = titleDto,
        author = authorDto,
        information = informationDto,
        summary = summaryDto,
        chapterList = chapterListDto,
        characterList = characterListDto,
        placeList = placeListDto,
    )

    val emptyScenarioDto = ScenarioDto(
        documentName = SCENARIO_DOCUMENT_NAME,
    )

    val exampleDescriptionDto = DescriptionDto(
        paragraphs = listOf("example"),
    )
}
