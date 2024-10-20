package com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.AuthorModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.CharacterListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.DescriptionModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.MainInfoModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.PlaceListModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.SummaryModel
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.entity.ScenarioBaseLocalDb
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.relation.ScenarioLocalDb

internal class ScenarioMapper(
    private val chapterMapper: ChapterMapper,
    private val characterMapper: CharacterMapper,
    private val informationMapper: InformationMapper,
    private val placeMapper: PlaceMapper,
) {

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
}
