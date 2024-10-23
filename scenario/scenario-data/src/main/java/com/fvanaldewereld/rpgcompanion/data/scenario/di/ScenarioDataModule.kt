package com.fvanaldewereld.rpgcompanion.data.scenario.di

import androidx.room.Room
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.DbScenarioRepository
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.repositories.GoogleDocsRepository
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.AuthorMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.ChapterListMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.ChapterMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.CharacterListMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.CharacterMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.DescriptionMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.InformationMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.MainInfoMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.PlaceListMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.PlaceMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.ScenarioMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.SummaryMapper
import com.fvanaldewereld.rpgcompanion.data.scenario.mapper.TitleMapper
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

    // Mapper
    singleOf(::AuthorMapper)
    singleOf(::ChapterListMapper)
    singleOf(::ChapterMapper)
    singleOf(::CharacterListMapper)
    singleOf(::CharacterMapper)
    singleOf(::DescriptionMapper)
    singleOf(::InformationMapper)
    singleOf(::MainInfoMapper)
    singleOf(::PlaceListMapper)
    singleOf(::PlaceMapper)
    singleOf(::SummaryMapper)
    singleOf(::TitleMapper)
    factoryOf(::ScenarioMapper)

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
