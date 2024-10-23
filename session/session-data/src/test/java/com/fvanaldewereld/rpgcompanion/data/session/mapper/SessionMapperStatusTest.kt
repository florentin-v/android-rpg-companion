package com.fvanaldewereld.rpgcompanion.data.session.mapper

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionStatus as SessionStatusDomain
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionStatus as SessionStatusData

class SessionMapperStatusTest {

    private lateinit var sessionStatusMapper: SessionStatusMapper

    @BeforeEach
    fun setUp() {
        sessionStatusMapper = SessionStatusMapper()
    }

    // region toDomain

    @Test
    fun `WHEN execute toDomain with SessionStatusData default value THEN return SessionStatusDomain default value `() {
        // WHEN
        val sessionStatusData = SessionStatusData.default

        val sessionStatusDomain = sessionStatusMapper.toDomain(sessionStatusData)

        // THEN
        val expectedSessionStatusDomain = SessionStatusDomain.default

        assertEquals(
            actual = sessionStatusDomain,
            expected = expectedSessionStatusDomain,
        )
    }

    @Test
    fun `WHEN execute toDomain with SessionStatusData NOT_STARTED THEN return SessionStatusDomain NOT_STARTED `() {
        // WHEN
        val sessionStatusData = SessionStatusData.NOT_STARTED

        val sessionStatusDomain = sessionStatusMapper.toDomain(sessionStatusData)

        // THEN
        val expectedSessionStatusDomain = SessionStatusDomain.NOT_STARTED

        assertEquals(
            actual = sessionStatusDomain,
            expected = expectedSessionStatusDomain,
        )
    }

    @Test
    fun `WHEN execute toDomain with SessionStatusData PENDING THEN return SessionStatusDomain PENDING `() {
        // WHEN
        val sessionStatusData = SessionStatusData.PENDING

        val sessionStatusDomain = sessionStatusMapper.toDomain(sessionStatusData)

        // THEN
        val expectedSessionStatusDomain = SessionStatusDomain.PENDING

        assertEquals(
            actual = sessionStatusDomain,
            expected = expectedSessionStatusDomain,
        )
    }

    @Test
    fun `WHEN execute toDomain with SessionStatusData FINISHED THEN return SessionStatusDomain FINISHED `() {
        // WHEN
        val sessionStatusData = SessionStatusData.FINISHED

        val sessionStatusDomain = sessionStatusMapper.toDomain(sessionStatusData)

        // THEN
        val expectedSessionStatusDomain = SessionStatusDomain.FINISHED

        assertEquals(
            actual = sessionStatusDomain,
            expected = expectedSessionStatusDomain,
        )
    }

    @Test
    fun `WHEN execute toDomain with different SessionStatusData name THEN return SessionStatusDomain default value `() {
        // WHEN
        val sessionStatusData = mock(SessionStatusData::class.java)
        whenever(sessionStatusData.name)
            .thenReturn("different name")

        val sessionStatusDomain = sessionStatusMapper.toDomain(sessionStatusData)

        // THEN
        val expectedSessionStatusDomain = SessionStatusDomain.default

        assertEquals(
            actual = sessionStatusDomain,
            expected = expectedSessionStatusDomain,
        )
    }

    // endregion toDomain

    // region toDataLocalDb

    @Test
    fun `WHEN execute toDataLocalDb with SessionStatusDomain default value THEN return SessionStatusData default value `() {
        // WHEN
        val sessionStatusDomain = SessionStatusDomain.default

        val sessionStatusData = sessionStatusMapper.toDataLocalDb(sessionStatusDomain)

        // THEN
        val expectedSessionStatusData = SessionStatusData.default

        assertEquals(
            actual = sessionStatusData,
            expected = expectedSessionStatusData,
        )
    }

    @Test
    fun `WHEN execute toDataLocalDb with SessionStatusDomain NOT_STARTED THEN return SessionStatusData NOT_STARTED `() {
        // WHEN
        val sessionStatusDomain = SessionStatusDomain.NOT_STARTED

        val sessionStatusData = sessionStatusMapper.toDataLocalDb(sessionStatusDomain)

        // THEN
        val expectedSessionStatusData = SessionStatusData.NOT_STARTED

        assertEquals(
            actual = sessionStatusData,
            expected = expectedSessionStatusData,
        )
    }

    @Test
    fun `WHEN execute toDataLocalDb with SessionStatusDomain PENDING THEN return SessionStatusData PENDING `() {
        // WHEN
        val sessionStatusDomain = SessionStatusDomain.PENDING

        val sessionStatusData = sessionStatusMapper.toDataLocalDb(sessionStatusDomain)

        // THEN
        val expectedSessionStatusData = SessionStatusData.PENDING

        assertEquals(
            actual = sessionStatusData,
            expected = expectedSessionStatusData,
        )
    }

    @Test
    fun `WHEN execute toDataLocalDb with SessionStatusDomain FINISHED THEN return SessionStatusData FINISHED `() {
        // WHEN
        val sessionStatusDomain = SessionStatusDomain.FINISHED

        val sessionStatusData = sessionStatusMapper.toDataLocalDb(sessionStatusDomain)

        // THEN
        val expectedSessionStatusData = SessionStatusData.FINISHED

        assertEquals(
            actual = sessionStatusData,
            expected = expectedSessionStatusData,
        )
    }

    @Test
    fun `WHEN execute toDataLocalDb with different SessionStatusDomain name THEN return SessionStatusData default value `() {
        // WHEN
        val sessionStatusDomain = mock(SessionStatusDomain::class.java)
        whenever(sessionStatusDomain.name)
            .thenReturn("different name")

        val sessionStatusData = sessionStatusMapper.toDataLocalDb(sessionStatusDomain)

        // THEN
        val expectedSessionStatusData = SessionStatusData.default

        assertEquals(
            actual = sessionStatusData,
            expected = expectedSessionStatusData,
        )
    }

    // endregion toDataLocalDb
}
