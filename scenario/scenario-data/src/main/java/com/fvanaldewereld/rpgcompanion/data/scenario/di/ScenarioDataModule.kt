package com.fvanaldewereld.rpgcompanion.data.scenario.di

import androidx.room.Room
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.GoogleDocsRepository
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.ChapterMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.ChapterMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.CharacterMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.CharacterMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.InformationMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.InformationMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.PlaceMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.PlaceMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.ScenarioMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers.ScenarioMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dtoMappers.ScenarioDtoMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dtoMappers.ScenarioDtoMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.AuthorModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.AuthorModelMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.ChapterModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.ChapterModelMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.ChaptersModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.ChaptersModelMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.CharacterModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.CharacterModelMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.CharactersModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.CharactersModelMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.DescriptionModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.DescriptionModelMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.InformationModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.InformationModelMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.MainInfoModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.MainInfoModelMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.PlaceModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.PlaceModelMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.PlacesModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.PlacesModelMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.ScenarioModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.ScenarioModelMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.SummaryModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.SummaryModelMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.TitleModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mappers.modelMappers.TitleModelMapperImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.repositories.GoogleDocsRepositoryImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.repositories.LocalDbScenarioRepositoryImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.googleDocs.GoogleDocsDataSource
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.googleDocs.GoogleDocsDataSourceImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.googleDocs.service.GoogleDocsService
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.ScenarioDatabase
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.ChapterDao
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.CharacterDao
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.PlaceDao
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.ScenarioBaseDao
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.ScenarioDao
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.dao.ScenarioDaoImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val SCENARIO_DATA_MODULE = module {

    // Model Mappers
    single<AuthorModelMapper> { AuthorModelMapperImpl() }
    single<ChapterModelMapper> { ChapterModelMapperImpl() }
    single<ChaptersModelMapper> { ChaptersModelMapperImpl() }
    single<CharacterModelMapper> { CharacterModelMapperImpl() }
    single<CharactersModelMapper> { CharactersModelMapperImpl() }
    single<DescriptionModelMapper> { DescriptionModelMapperImpl() }
    single<InformationModelMapper> { InformationModelMapperImpl() }
    single<MainInfoModelMapper> { MainInfoModelMapperImpl() }
    single<PlaceModelMapper> { PlaceModelMapperImpl() }
    single<PlacesModelMapper> { PlacesModelMapperImpl() }
    single<ScenarioModelMapper> { ScenarioModelMapperImpl() }
    single<SummaryModelMapper> { SummaryModelMapperImpl() }
    single<TitleModelMapper> { TitleModelMapperImpl() }

    // DtoMapper
    factory<ScenarioDtoMapper> { ScenarioDtoMapperImpl() }

    // DbObjectMapper
    single<ScenarioMapper> { ScenarioMapperImpl() }
    single<ChapterMapper> { ChapterMapperImpl() }
    single<CharacterMapper> { CharacterMapperImpl() }
    single<InformationMapper> { InformationMapperImpl() }
    single<PlaceMapper> { PlaceMapperImpl() }

    // Sources
    factory<GoogleDocsService> {
        GoogleDocsService(
            androidContext(),
        )
    }
    factory<GoogleDocsDataSource> { GoogleDocsDataSourceImpl() }
    single<ScenarioDatabase> {
        Room.databaseBuilder(
            androidContext(),
            ScenarioDatabase::class.java,
            "scenario-database",
        ).build()
    }
    single<ScenarioBaseDao> { get<ScenarioDatabase>().scenarioBaseDao() }
    single<ScenarioDao> { ScenarioDaoImpl() }
    single<ChapterDao> { get<ScenarioDatabase>().chapterDao() }
    single<CharacterDao> { get<ScenarioDatabase>().characterDao() }
    single<PlaceDao> { get<ScenarioDatabase>().placeDao() }

// Repositories
    factory<GoogleDocsRepository> { GoogleDocsRepositoryImpl() }
    single<DbScenarioRepository> { LocalDbScenarioRepositoryImpl() }
}
