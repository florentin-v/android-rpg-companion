package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.AuthorModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChaptersModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharactersModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlacesModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.SummaryModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.ScenarioBase
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.relations.Scenario
import org.koin.core.context.GlobalContext

interface ScenarioMapper : DbObjectMapper<Scenario, ScenarioModel>

internal class ScenarioMapperImpl : ScenarioMapper {

    private val chapterMapper: ChapterMapper by GlobalContext.get().inject()
    private val characterMapper: CharacterMapper by GlobalContext.get().inject()
    private val informationMapper: InformationMapper by GlobalContext.get().inject()
    private val placeMapper: PlaceMapper by GlobalContext.get().inject()

    override fun to(from: Scenario) = ScenarioModel(
        id = from.scenarioBase.id,
        author = from.scenarioBase.author?.let { author ->
            AuthorModel(name = author)
        },
        chapters = from.chapters?.let { chapters ->
            ChaptersModel(
                chapters = chapters.map { chapter ->
                    chapterMapper.to(from = chapter)
                },
            )
        },
        characters = from.characters?.let { characters ->
            CharactersModel(
                characters = characters.map { character ->
                    characterMapper.to(from = character)
                },
            )
        },
        documentName = from.scenarioBase.documentName,
        information = from.scenarioBase.information?.let { information ->
            informationMapper.to(from = information)
        },
        places = from.places?.let { places ->
            PlacesModel(
                places = places.map { place ->
                    placeMapper.to(from = place)
                },
            )
        },
        summary = from.scenarioBase.summary?.let { summary ->
            SummaryModel(text = DescriptionModel(summary), tt = "dd")
        },
        title = from.scenarioBase.title?.let { title ->
            TitleModel(title = title)
        },
    )

    override fun from(to: ScenarioModel): Scenario {
        val chapters = to.chapters?.chapters?.map { chapterMapper.from(it) }
        val characters = to.characters?.characters?.map { characterMapper.from(it) }
        val places = to.places?.places?.map { placeMapper.from(it) }
        val scenarioBase: ScenarioBase = to.id?.let { id ->
            ScenarioBase(
                id = id,
                author = to.author?.name,
                documentName = to.documentName,
                information = to.information?.let { information ->
                    informationMapper.from(to = information)
                },
                summary = to.summary?.text?.paragraphs,
                title = to.title?.title,
            )
        } ?: ScenarioBase(
            author = to.author?.name,
            documentName = to.documentName,
            information = to.information?.let { information ->
                informationMapper.from(to = information)
            },
            summary = to.summary?.text?.paragraphs,
            title = to.title?.title,
        )

        return Scenario(
            chapters = chapters,
            characters = characters,
            places = places,
            scenarioBase = scenarioBase,
        )
    }
}
