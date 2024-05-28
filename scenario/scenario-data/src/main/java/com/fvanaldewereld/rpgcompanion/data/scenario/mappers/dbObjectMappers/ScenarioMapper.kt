package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.AuthorModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChaptersModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharactersModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.MainInfoModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlacesModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.SummaryModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.entities.ScenarioBase
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.relations.Scenario
import org.koin.core.context.GlobalContext

interface ScenarioMapper : DbObjectMapper<Scenario, ScenarioModel>

class ScenarioMapperImpl : ScenarioMapper {

    private val chapterMapper: ChapterMapper by GlobalContext.get().inject()
    private val characterMapper: CharacterMapper by GlobalContext.get().inject()
    private val informationMapper: InformationMapper by GlobalContext.get().inject()
    private val placeMapper: PlaceMapper by GlobalContext.get().inject()

    override fun to(from: Scenario) = ScenarioModel(
        id = from.scenarioBase.id,
        documentName = from.scenarioBase.documentName,
        mainInfo = MainInfoModel(
            author = AuthorModel(name = from.scenarioBase.author),
            information = informationMapper.to(from = from.scenarioBase.information),
            summary = SummaryModel(text = DescriptionModel(from.scenarioBase.summary)),
            title = TitleModel(value = from.scenarioBase.title),
        ),
        chapters = ChaptersModel(
            chapters = from.chapters.map { chapter ->
                chapterMapper.to(from = chapter)
            },
        ),
        characters = CharactersModel(
            characters = from.characters.map { character ->
                characterMapper.to(from = character)
            },
        ),
        places = PlacesModel(
            places = from.places.map { place ->
                placeMapper.to(from = place)
            },
        ),
    )

    override fun from(to: ScenarioModel): Scenario {
        val chapters = to.chapters.chapters.map { chapterMapper.from(it) }
        val characters = to.characters.characters.map { characterMapper.from(it) }
        val places = to.places.places.map { placeMapper.from(it) }
        val scenarioBase = to.id?.let {
            ScenarioBase(
                id = it,
                author = to.mainInfo.author.name,
                documentName = to.documentName,
                information = informationMapper.from(to = to.mainInfo.information),
                summary = to.mainInfo.summary.text.paragraphs,
                title = to.mainInfo.title.value,
            )
        } ?: ScenarioBase(
            author = to.mainInfo.author.name,
            documentName = to.documentName,
            information = informationMapper.from(to = to.mainInfo.information),
            summary = to.mainInfo.summary.text.paragraphs,
            title = to.mainInfo.title.value,
        )

        return Scenario(
            chapters = chapters,
            characters = characters,
            places = places,
            scenarioBase = scenarioBase,
        )
    }
}
