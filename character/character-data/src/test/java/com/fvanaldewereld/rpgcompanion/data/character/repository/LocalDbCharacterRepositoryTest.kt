package com.fvanaldewereld.rpgcompanion.data.character.repository

import com.fvanaldewereld.rpgcompanion.api.domain.character.model.CharacterModel
import com.fvanaldewereld.rpgcompanion.data.character.mapper.CharacterMapper
import com.fvanaldewereld.rpgcompanion.data.character.source.localDb.dao.CharacterDao
import com.fvanaldewereld.rpgcompanion.data.character.source.localDb.entity.CharacterLocalDb
import com.fvanaldewereld.rpgcompanion.test.common.BasicKoinTest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verifyBlocking
import org.mockito.kotlin.whenever

class LocalDbCharacterRepositoryTest : BasicKoinTest() {

    private val mockCharacterDao by inject<CharacterDao>()
    private val mockCharacterMapper by inject<CharacterMapper>()

    private lateinit var localDbCharacterRepository: LocalDbCharacterRepository

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<CharacterDao>() }
                single { mock<CharacterMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        localDbCharacterRepository = LocalDbCharacterRepository(
            characterDao = mockCharacterDao,
            characterMapper = mockCharacterMapper,
        )
    }

    @Test
    fun `WHEN execute addCharacter THEN call insert from characterDao`() =
        runBlocking {
            // GIVEN
            val characterModel = mock<CharacterModel>()
            val characterLocalDb = mock<CharacterLocalDb>()
            whenever(mockCharacterMapper.toDataLocalDb(characterModel))
                .thenReturn(characterLocalDb)

            // WHEN
            localDbCharacterRepository.addCharacter(characterModel = characterModel)

            // THEN
            verifyBlocking(mockCharacterDao) {
                insert(characterLocalDb = characterLocalDb)
            }
        }

    @Test
    fun `WHEN execute getAllCharacterList THEN call getAllCharacterList from characterDao`() =
        runBlocking {
            // GIVEN
            whenever(mockCharacterDao.getAllCharacterList())
                .thenReturn(listOf(mock<CharacterLocalDb>()))
            whenever(mockCharacterMapper.toDomain(any<CharacterLocalDb>()))
                .thenReturn(mock<CharacterModel>())

            // WHEN
            localDbCharacterRepository.getAllCharacterList()

            // THEN
            verifyBlocking(mockCharacterDao) {
                getAllCharacterList()
            }
        }

    @Test
    fun `WHEN execute getAllCharacterListByGameId THEN call getAllCharacterListByGameId from characterDao`() =
        runBlocking {
            // GIVEN
            whenever(mockCharacterDao.getAllCharacterListByGameId(any<Long>()))
                .thenReturn(listOf(mock<CharacterLocalDb>()))
            whenever(mockCharacterMapper.toDomain(any<CharacterLocalDb>()))
                .thenReturn(mock<CharacterModel>())

            // WHEN
            val selectedGameId = 4L
            localDbCharacterRepository.getAllCharacterListByGameId(gameId = selectedGameId)

            // THEN
            verifyBlocking(mockCharacterDao) {
                getAllCharacterListByGameId(selectedGameId)
            }
        }

    @Test
    fun `WHEN execute getLastCharacterList THEN call getLastCharacterList from characterDao`() =
        runBlocking {
            // GIVEN
            whenever(mockCharacterDao.getLastCharacterList(any<Int>()))
                .thenReturn(listOf(mock<CharacterLocalDb>()))
            whenever(mockCharacterMapper.toDomain(any<CharacterLocalDb>()))
                .thenReturn(mock<CharacterModel>())

            // WHEN
            val number = 1
            localDbCharacterRepository.getLastCharacterList(number = number)

            // THEN
            verifyBlocking(mockCharacterDao) {
                getLastCharacterList(number = number)
            }
        }

    @Test
    fun `WHEN execute getCharacterById THEN call getCharacterById from characterDao`() =
        runBlocking {
            // GIVEN
            whenever(mockCharacterDao.getCharacterById(any<Long>()))
                .thenReturn(mock<CharacterLocalDb>())
            whenever(mockCharacterMapper.toDomain(any<CharacterLocalDb>()))
                .thenReturn(mock<CharacterModel>())

            // WHEN
            val characterId = 1L
            localDbCharacterRepository.getCharacterById(characterId = characterId)

            // THEN
            verifyBlocking(mockCharacterDao) {
                getCharacterById(characterId = characterId)
            }
        }

    @Test
    fun `WHEN execute deleteByCharacterId THEN call delete from characterDao`() =
        runBlocking {
            // GIVEN
            val characterId = 1L
            whenever(mockCharacterDao.getCharacterById(any<Long>()))
                .thenReturn(mock<CharacterLocalDb>())

            // WHEN
            localDbCharacterRepository.deleteByCharacterId(characterId = characterId)

            // THEN
            verifyBlocking(mockCharacterDao) {
                getCharacterById(characterId = characterId)
                delete(characterLocalDb = any<CharacterLocalDb>())
            }
        }
}
