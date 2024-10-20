package com.fvanaldewereld.rpgcompanion.data.scenario.di

import androidx.room.Room
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.GoogleDocsRepository
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.dto.ScenarioDtoMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb.ChapterMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb.CharacterMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb.InformationMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb.PlaceMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.localDb.ScenarioMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.AuthorModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.ChapterListModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.ChapterModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.CharacterListModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.CharacterModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.DescriptionModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.InformationModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.MainInfoModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.PlaceListModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.PlaceModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.ScenarioModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.SummaryModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.model.TitleModelMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.repository.GoogleDocsRepositoryImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.repository.LocalDbScenarioRepository
import com.fvanaldewereld.rpgcompanion.data.scenario.source.googleDocs.GoogleDocsDataSource
import com.fvanaldewereld.rpgcompanion.data.scenario.source.googleDocs.GoogleDocsDataSourceImpl
import com.fvanaldewereld.rpgcompanion.data.scenario.source.googleDocs.service.GoogleDocsService
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.ScenarioDatabase
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao.ScenarioDao
import com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.dao.ScenarioDaoImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val SCENARIO_DATA_MODULE = module {

    // Model Mapper
    singleOf(::AuthorModelMapper)
    singleOf(::ChapterModelMapper)
    singleOf(::ChapterListModelMapper)
    singleOf(::CharacterModelMapper)
    singleOf(::CharacterListModelMapper)
    singleOf(::DescriptionModelMapper)
    singleOf(::InformationModelMapper)
    singleOf(::MainInfoModelMapper)
    singleOf(::PlaceModelMapper)
    singleOf(::PlaceListModelMapper)
    singleOf(::ScenarioModelMapper)
    singleOf(::SummaryModelMapper)
    singleOf(::TitleModelMapper)

    // Dto Mapper
    factoryOf(::ScenarioDtoMapper)

    // LocalDb Mapper
    singleOf(::ScenarioMapper)
    singleOf(::ChapterMapper)
    singleOf(::CharacterMapper)
    singleOf(::InformationMapper)
    singleOf(::PlaceMapper)

    // Source
    factoryOf(::GoogleDocsService)
    factoryOf(::GoogleDocsDataSourceImpl) bind GoogleDocsDataSource::class
    single {
        Room.databaseBuilder(
            androidContext(),
            ScenarioDatabase::class.java,
            ScenarioDatabase.DATABASE_NAME,
        ).build()
    }
    singleOf(ScenarioDatabase::scenarioBaseDao)
    singleOf(ScenarioDatabase::chapterDao)
    singleOf(ScenarioDatabase::characterDao)
    singleOf(ScenarioDatabase::placeDao)
    singleOf(::ScenarioDaoImpl) bind ScenarioDao::class

    // Repository
    factoryOf(::GoogleDocsRepositoryImpl) bind GoogleDocsRepository::class
    singleOf(::LocalDbScenarioRepository) bind DbScenarioRepository::class
}
