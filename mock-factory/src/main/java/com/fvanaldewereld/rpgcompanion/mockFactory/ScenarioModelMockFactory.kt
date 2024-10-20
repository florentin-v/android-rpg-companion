@file:Suppress("kotlin:S1192")

package com.fvanaldewereld.rpgcompanion.mockFactory

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.AuthorModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.GenreListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.InformationModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.MainInfoModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.SummaryModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ThemeListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel

object ScenarioModelMockFactory {

    const val SCENARIO_ID: Long = 0

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
            "Aenean volutpat faucibus laoreet. " +
            "Maecenas dignissim et elit eu lobortis."

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
            "Etiam id diam erat. " +
            "Sed ac mauris vitae arcu vulputate volutpat. " +
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

    val titleModel = TitleModel(SCENARIO_TITLE)
    val authorModel = AuthorModel(SCENARIO_AUTHOR)
    val informationModel = InformationModel(
        nbPlayers = NUMBER_OF_PLAYERS_VALUE,
        genreList = GenreListModel(
            values = listOf(GENRES_HORROR, GENRES_DRAMA),
        ),
        themeList = ThemeListModel(
            values = listOf(THEMES_ALIEN, THEMES_POSSESSION, THEMES_PSYCOLOGICAL),
        ),
    )
    val summaryDescriptionModel = DescriptionModel(
        paragraphs = listOf(SCENARIO_SUMMARY_TEXT),
    )
    val summaryModel = SummaryModel(
        text = summaryDescriptionModel,
    )
    val mainInfoModel = MainInfoModel(
        title = titleModel,
        author = authorModel,
        information = informationModel,
        summary = summaryModel,
    )
    val chapterDescriptionModel = DescriptionModel(
        paragraphs = listOf(SCENARIO_CHAPTERS_INTRODUCTION_TEXT),
    )
    val chapterModel = ChapterModel(
        name = SCENARIO_CHAPTERS_INTRODUCTION,
        description = chapterDescriptionModel,
    )
    val chapterListModel = ChapterListModel(
        listOf(
            chapterModel,
        ),
    )
    val characterDescriptionModel1 = DescriptionModel(
        paragraphs = listOf(SCENARIO_CHARACTERS_DESCRIPTION_1),
    )
    val characterModel1 = CharacterModel(
        name = SCENARIO_CHARACTERS_NAME_1,
        description = characterDescriptionModel1,
    )
    private val characterDescriptionModel2 = DescriptionModel(
        paragraphs = listOf(SCENARIO_CHARACTERS_DESCRIPTION_2),
    )
    val characterModel2 = CharacterModel(
        name = SCENARIO_CHARACTERS_NAME_2,
        description = characterDescriptionModel2,
    )
    val characterListModel = CharacterListModel(
        list = listOf(
            characterModel1,
            characterModel2,
        ),
    )
    val placeDescriptionModel1 = DescriptionModel(
        paragraphs = listOf(SCENARIO_PLACES_DESCRIPTION_1),
    )
    val placeModel1 = PlaceModel(
        name = SCENARIO_PLACES_NAME_1,
        description = placeDescriptionModel1,
    )
    private val placeDescriptionModel2 = DescriptionModel(
        paragraphs = listOf(SCENARIO_PLACES_DESCRIPTION_2),
    )
    val placeModel2 = PlaceModel(
        name = SCENARIO_PLACES_NAME_2,
        description = placeDescriptionModel2,
    )
    val placeListModel = PlaceListModel(
        list = listOf(
            placeModel1,
            placeModel2,
        ),
    )

    val scenarioModelWithoutId = ScenarioModel(
        documentName = SCENARIO_DOCUMENT_NAME,
        mainInfo = mainInfoModel,
        chapterList = chapterListModel,
        characterList = characterListModel,
        placeList = placeListModel,
    )

    val scenarioModelWithId = ScenarioModel(
        id = SCENARIO_ID,
        documentName = SCENARIO_DOCUMENT_NAME,
        mainInfo = mainInfoModel,
        chapterList = chapterListModel,
        characterList = characterListModel,
        placeList = placeListModel,
    )

    val emptyScenarioModelWithoutId = ScenarioModel(
        documentName = SCENARIO_DOCUMENT_NAME,
    )

    val emptyScenarioModelWithId = ScenarioModel(
        id = SCENARIO_ID,
        documentName = SCENARIO_DOCUMENT_NAME,
    )

    val exampleDescriptionModel = DescriptionModel(
        paragraphs = listOf("example"),
    )
}
