package com.fvanaldewereld.rpgcompanion.data.session.repository

import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import com.fvanaldewereld.rpgcompanion.data.session.mapper.SessionMapper
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.dao.SessionDao
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionLocalDb
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

class LocalDbSessionRepositoryTest : BasicKoinTest() {

    private val mockSessionDao by inject<SessionDao>()
    private val mockSessionMapper by inject<SessionMapper>()

    private lateinit var localDbSessionRepository: LocalDbSessionRepository

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<SessionDao>() }
                single { mock<SessionMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        localDbSessionRepository = LocalDbSessionRepository(
            sessionDao = mockSessionDao,
            sessionMapper = mockSessionMapper,
        )
    }

    @Test
    fun `WHEN execute addSession THEN call insert from SessionDao`() =
        runBlocking {
            // GIVEN
            val sessionModel = mock<SessionModel>()
            val sessionLocalDb = mock<SessionLocalDb>()
            whenever(mockSessionMapper.toDataLocalDb(sessionModel))
                .thenReturn(sessionLocalDb)

            // WHEN
            localDbSessionRepository.addSession(sessionModel = sessionModel)

            // THEN
            verifyBlocking(mockSessionDao) {
                insert(sessionLocalDb = sessionLocalDb)
            }
        }

    @Test
    fun `WHEN execute getAllSessionList THEN call getAllSessionList from SessionDao`() =
        runBlocking {
            // GIVEN
            whenever(mockSessionDao.getAllSessionList())
                .thenReturn(listOf(mock<SessionLocalDb>()))
            whenever(mockSessionMapper.toDomain(any<SessionLocalDb>()))
                .thenReturn(mock<SessionModel>())

            // WHEN
            localDbSessionRepository.getAllSessionList()

            // THEN
            verifyBlocking(mockSessionDao) {
                getAllSessionList()
            }
        }

    @Test
    fun `WHEN execute getAllSessionListByGameId THEN call getAllSessionListByGameId from SessionDao`() =
        runBlocking {
            // GIVEN
            whenever(mockSessionDao.getAllSessionListByGameId(any<Long>()))
                .thenReturn(listOf(mock<SessionLocalDb>()))
            whenever(mockSessionMapper.toDomain(any<SessionLocalDb>()))
                .thenReturn(mock<SessionModel>())

            // WHEN
            val selectedGameId = 4L
            localDbSessionRepository.getAllSessionListByGameId(gameId = selectedGameId)

            // THEN
            verifyBlocking(mockSessionDao) {
                getAllSessionListByGameId(selectedGameId)
            }
        }

    @Test
    fun `WHEN execute getLastSessionList THEN call getLastSessionList from SessionDao`() =
        runBlocking {
            // GIVEN
            whenever(mockSessionDao.getLastSessionList(any<Int>()))
                .thenReturn(listOf(mock<SessionLocalDb>()))
            whenever(mockSessionMapper.toDomain(any<SessionLocalDb>()))
                .thenReturn(mock<SessionModel>())

            // WHEN
            val number = 1
            localDbSessionRepository.getLastSessionList(number = number)

            // THEN
            verifyBlocking(mockSessionDao) {
                getLastSessionList(number = number)
            }
        }

    @Test
    fun `WHEN execute getSessionById THEN call getSessionById from SessionDao`() =
        runBlocking {
            // GIVEN
            whenever(mockSessionDao.getSessionById(any<Long>()))
                .thenReturn(mock<SessionLocalDb>())
            whenever(mockSessionMapper.toDomain(any<SessionLocalDb>()))
                .thenReturn(mock<SessionModel>())

            // WHEN
            val sessionId = 1L
            localDbSessionRepository.getSessionById(sessionId = sessionId)

            // THEN
            verifyBlocking(mockSessionDao) {
                getSessionById(sessionId = sessionId)
            }
        }

    @Test
    fun `WHEN execute deleteBySessionId THEN call delete from SessionDao`() =
        runBlocking {
            // GIVEN
            val sessionId = 1L
            whenever(mockSessionDao.getSessionById(any<Long>()))
                .thenReturn(mock<SessionLocalDb>())

            // WHEN
            localDbSessionRepository.deleteBySessionId(sessionId = sessionId)

            // THEN
            verifyBlocking(mockSessionDao) {
                getSessionById(sessionId = sessionId)
                delete(sessionLocalDb = any<SessionLocalDb>())
            }
        }
}
