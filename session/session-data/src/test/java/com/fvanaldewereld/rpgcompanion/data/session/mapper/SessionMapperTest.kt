package com.fvanaldewereld.rpgcompanion.data.session.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionLocalDb
import com.fvanaldewereld.rpgcompanion.test.common.BasicKoinTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.getValue
import kotlin.test.assertEquals
import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionStatus as SessionStatusDomain
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionStatus as SessionStatusData

class SessionMapperTest : BasicKoinTest() {

    private val mockSessionStatusMapper by inject<SessionStatusMapper>()

    private lateinit var sessionMapper: SessionMapper

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<SessionStatusMapper>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        sessionMapper = SessionMapper(
            sessionStatusMapper = mockSessionStatusMapper,
        )
    }

    @Test
    fun `WHEN execute toDomain with SessionLocalDb THEN return SessionModel`() {
        // GIVEN
        val mockSessionStatusData = SessionStatusData.default
        val expectedSessionStatusDomain = SessionStatusDomain.default
        whenever(mockSessionStatusMapper.toDomain(mockSessionStatusData))
            .thenReturn(expectedSessionStatusDomain)

        // WHEN
        val sessionLocalDb = SessionLocalDb(
            id = 1,
            title = "Test Session",
            gameId = 2,
            status = mockSessionStatusData,
        )
        val sessionModel = sessionMapper.toDomain(sessionLocalDb)

        // THEN
        val expectedSessionModel = SessionModel(
            id = 1,
            title = "Test Session",
            gameId = 2,
            status = expectedSessionStatusDomain,
        )

        assertEquals(
            actual = sessionModel,
            expected = expectedSessionModel,
        )
    }

    @Test
    fun `WHEN execute toDataLocalDb with SessionModel THEN return SessionLocalDb`() {
        // GIVEN
        val mockSessionStatusDomain = SessionStatusDomain.default
        val expectedSessionStatusData = SessionStatusData.default
        whenever(mockSessionStatusMapper.toDataLocalDb(mockSessionStatusDomain))
            .thenReturn(expectedSessionStatusData)

        // WHEN
        val sessionModel = SessionModel(
            id = 1,
            title = "Test Session",
            gameId = 2,
            status = mockSessionStatusDomain,
        )
        val sessionLocalDb = sessionMapper.toDataLocalDb(sessionModel)

        // THEN
        val expectedSessionLocalDb = SessionLocalDb(
            id = 1,
            title = "Test Session",
            gameId = 2,
            status = expectedSessionStatusData,
        )

        assertEquals(
            actual = sessionLocalDb,
            expected = expectedSessionLocalDb,
        )
    }
}
