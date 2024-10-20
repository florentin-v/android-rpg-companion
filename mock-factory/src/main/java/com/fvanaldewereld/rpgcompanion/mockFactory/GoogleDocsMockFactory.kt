@file:Suppress("kotlin:S1192")

package com.fvanaldewereld.rpgcompanion.mockFactory

import com.fvanaldewereld.rpgcompanion.data.scenario.dto.TextType
import com.google.api.services.docs.v1.model.Body
import com.google.api.services.docs.v1.model.Document
import com.google.api.services.docs.v1.model.Paragraph
import com.google.api.services.docs.v1.model.ParagraphElement
import com.google.api.services.docs.v1.model.ParagraphStyle
import com.google.api.services.docs.v1.model.StructuralElement
import com.google.api.services.docs.v1.model.TextRun
import java.net.URL

object GoogleDocsMockFactory {

    private const val SCENARIO_DOCUMENT_NAME = "Scenario - Example"

    private const val SCENARIO_TITLE = "Scenario"

    private const val SCENARIO_AUTHOR = "Author"

    private const val SCENARIO_SUMMARY = "Summary"

    private const val SCENARIO_SUMMARY_TEXT =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Sed sollicitudin malesuada condimentum. " +
            "Aenean varius turpis a ornare lobortis. " +
            "Curabitur massa turpis, commodo sit amet eleifend dignissim, porttitor ut dolor."

    private const val SCENARIO_CHAPTERS = "Chapters"

    private const val SCENARIO_CHAPTERS_INTRODUCTION = "Introduction"

    private const val SCENARIO_CHAPTERS_INTRODUCTION_TEXT =
        "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. " +
            "Proin aliquet at eros pretium porttitor. " +
            "Vestibulum in elit dictum, faucibus lacus et, fermentum ipsum. " +
            "Phasellus in commodo metus. " +
            "Pellentesque lacinia orci et nisi ultricies, id tempus urna porta."

    private const val SCENARIO_INFORMATION = "Information"

    private const val NUMBER_OF_PLAYERS_LABEL = "Number of players"

    private const val NUMBER_OF_PLAYERS_VALUE = 4

    private const val GENRES_LABEL = "Genres"

    private const val GENRES_HORROR = "Horror"

    private const val GENRES_DRAMA = "Drama"

    private const val THEMES_LABEL = "Themes"

    private const val THEMES_ALIEN = "Alien"

    private const val THEMES_POSSESSION = "Possession"

    private const val THEMES_PSYCOLOGICAL = "Psycological"

    private const val SCENARIO_CHARACTERS = "Characters"

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

    private const val SCENARIO_PLACES = "Places"

    private const val SCENARIO_PLACES_NAME_1 = "Place 1"

    private const val SCENARIO_PLACES_NAME_2 = "Place 2"

    private const val SCENARIO_PLACES_DESCRIPTION_1 =
        "Nullam sit amet dui non est auctor interdum eu ut velit. " +
            "Etiam id diam erat. " +
            "Sed ac mauris vitae arcu vulputate volutpat. " +
            "Maecenas ut nulla vestibulum purus placerat rutrum. " +
            "Quisque tellus ex, aliquet fermentum egestas aliquam, tincidunt eget sem. " +
            "Praesent arcu felis, scelerisque vitae velit ornare, venenatis consectetur lacus. " +
            "Curabitur consequat ipsum ac dapibus tincidunt. Pellentesque et sem mauris. " +
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

    val googleDocsUrl = URL(
        "https://docs.google.com/document/d/1EAJa9JbA9tbGNbyv5AHIherdT34C4Ks6hKyIyDlZ7Os/edit#heading=h.6srcydx8zv6e",
    )

    const val GOOGLE_DOCS_DOCUMENT_ID = "1EAJa9JbA9tbGNbyv5AHIherdT34C4Ks6hKyIyDlZ7Os"

    val googleDocsDocument: Document = Document()
        .setTitle(SCENARIO_DOCUMENT_NAME)
        .setBody(
            Body().setContent(
                listOf(
                    getStructuralElement(
                        namedStyleType = TextType.TITLE.name,
                        content = SCENARIO_TITLE,
                    ),

                    getStructuralElement(
                        namedStyleType = TextType.SUBTITLE.name,
                        content = SCENARIO_AUTHOR,
                    ),

                    getStructuralElement(
                        namedStyleType = TextType.HEADING_1.name,
                        content = SCENARIO_INFORMATION,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.NORMAL_TEXT.name,
                        content = "$NUMBER_OF_PLAYERS_LABEL : $NUMBER_OF_PLAYERS_VALUE",
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.NORMAL_TEXT.name,
                        content = "$GENRES_LABEL : $GENRES_HORROR / $GENRES_DRAMA",
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.NORMAL_TEXT.name,
                        content = "$THEMES_LABEL : $THEMES_ALIEN / $THEMES_POSSESSION / $THEMES_PSYCOLOGICAL",
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.HEADING_1.name,
                        content = SCENARIO_SUMMARY,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.NORMAL_TEXT.name,
                        content = SCENARIO_SUMMARY_TEXT,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.HEADING_1.name,
                        content = SCENARIO_CHARACTERS,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.HEADING_2.name,
                        content = SCENARIO_CHARACTERS_NAME_1,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.NORMAL_TEXT.name,
                        content = SCENARIO_CHARACTERS_DESCRIPTION_1,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.HEADING_2.name,
                        content = SCENARIO_CHARACTERS_NAME_2,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.NORMAL_TEXT.name,
                        content = SCENARIO_CHARACTERS_DESCRIPTION_2,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.HEADING_1.name,
                        content = SCENARIO_PLACES,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.HEADING_2.name,
                        content = SCENARIO_PLACES_NAME_1,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.NORMAL_TEXT.name,
                        content = SCENARIO_PLACES_DESCRIPTION_1,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.HEADING_2.name,
                        content = SCENARIO_PLACES_NAME_2,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.NORMAL_TEXT.name,
                        content = SCENARIO_PLACES_DESCRIPTION_2,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.HEADING_1.name,
                        content = SCENARIO_CHAPTERS,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.HEADING_2.name,
                        content = SCENARIO_CHAPTERS_INTRODUCTION,
                    ),
                    getStructuralElement(
                        namedStyleType = TextType.NORMAL_TEXT.name,
                        content = SCENARIO_CHAPTERS_INTRODUCTION_TEXT,
                    ),
                ),
            ),
        )

    val emptyGoogleDocsDocument: Document = Document()
        .setTitle(SCENARIO_DOCUMENT_NAME)
        .setBody(
            Body().setContent(
                emptyList(),
            ),
        )

    private fun getStructuralElement(namedStyleType: String, content: String): StructuralElement =
        StructuralElement().setParagraph(
            Paragraph()
                .setParagraphStyle(ParagraphStyle().setNamedStyleType(namedStyleType))
                .setElements(
                    listOf(
                        ParagraphElement().setTextRun(TextRun().setContent(content)),
                    ),
                ),
        )
}
