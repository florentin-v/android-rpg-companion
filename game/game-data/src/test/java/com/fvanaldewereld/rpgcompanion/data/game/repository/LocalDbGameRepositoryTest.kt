package com.fvanaldewereld.rpgcompanion.data.game.repository

import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.data.game.mapper.GameMapper
import com.fvanaldewereld.rpgcompanion.data.game.source.localDb.dao.GameDao
import com.fvanaldewereld.rpgcompanion.data.game.source.localDb.entity.GameLocalDb
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

class LocalDbGameRepositoryTest : BasicKoinTest() {

    private val mockGameDao by inject<GameDao>()
    private val mockGameMapper by inject<GameMapper>()

    private lateinit var localDbGameRepository: LocalDbGameRepository

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<GameDao>() }
                single { mock<GameMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        localDbGameRepository = LocalDbGameRepository(
            gameDao = mockGameDao,
            gameMapper = mockGameMapper,
        )
    }

    @Test
    fun `WHEN execute addGame THEN call insert from gameDao`() =
        runBlocking {
            // GIVEN
            val gameModel = mock<GameModel>()
            val gameLocalDb = mock<GameLocalDb>()
            whenever(mockGameMapper.toDataLocalDb(gameModel))
                .thenReturn(gameLocalDb)

            // WHEN
            localDbGameRepository.addGame(gameModel = gameModel)

            // THEN
            verifyBlocking(mockGameDao) {
                insert(gameLocalDb = gameLocalDb)
            }
        }

    @Test
    fun `WHEN execute getAllGameList THEN call getAllGameList from gameDao`() =
        runBlocking {
            // GIVEN
            whenever(mockGameDao.getAllGameList())
                .thenReturn(listOf(mock<GameLocalDb>()))
            whenever(mockGameMapper.toDomain(any<GameLocalDb>()))
                .thenReturn(mock<GameModel>())

            // WHEN
            localDbGameRepository.getAllGameList()

            // THEN
            verifyBlocking(mockGameDao) {
                getAllGameList()
            }
        }

    @Test
    fun `WHEN execute getLastGameList THEN call getLastGameList from gameDao`() =
        runBlocking {
            // GIVEN
            whenever(mockGameDao.getLastGameList(any<Int>()))
                .thenReturn(listOf(mock<GameLocalDb>()))
            whenever(mockGameMapper.toDomain(any<GameLocalDb>()))
                .thenReturn(mock<GameModel>())

            // WHEN
            val number = 1
            localDbGameRepository.getLastGameList(number = number)

            // THEN
            verifyBlocking(mockGameDao) {
                getLastGameList(number = number)
            }
        }

    @Test
    fun `WHEN execute getGameById THEN call getGameById from gameDao`() =
        runBlocking {
            // GIVEN
            whenever(mockGameDao.getGameById(any<Long>()))
                .thenReturn(mock<GameLocalDb>())
            whenever(mockGameMapper.toDomain(any<GameLocalDb>()))
                .thenReturn(mock<GameModel>())

            // WHEN
            val gameId = 1L
            localDbGameRepository.getGameById(gameId = gameId)

            // THEN
            verifyBlocking(mockGameDao) {
                getGameById(gameId = gameId)
            }
        }

    @Test
    fun `WHEN execute deleteByGameId THEN call delete from gameDao`() =
        runBlocking {
            // GIVEN
            val gameId = 1L
            whenever(mockGameDao.getGameById(any<Long>()))
                .thenReturn(mock<GameLocalDb>())

            // WHEN
            localDbGameRepository.deleteByGameId(gameId = gameId)

            // THEN
            verifyBlocking(mockGameDao) {
                getGameById(gameId = gameId)
                delete(gameLocalDb = any<GameLocalDb>())
            }
        }
}
