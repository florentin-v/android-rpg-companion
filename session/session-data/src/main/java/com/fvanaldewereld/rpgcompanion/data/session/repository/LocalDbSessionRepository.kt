package com.fvanaldewereld.rpgcompanion.data.session.repository

import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import com.fvanaldewereld.rpgcompanion.api.domain.session.repository.DbSessionRepository
import com.fvanaldewereld.rpgcompanion.data.session.mapper.SessionMapper
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.dao.SessionDao

internal class LocalDbSessionRepository(
    private val sessionDao: SessionDao,
    private val sessionMapper: SessionMapper,
) : DbSessionRepository {

    override suspend fun addSession(sessionModel: SessionModel) = sessionDao
        .insert(
            sessionLocalDb = sessionMapper.toDataLocalDb(from = sessionModel),
        )

    override suspend fun getAllSessionList(): List<SessionModel> = sessionDao
        .getAllSessionList()
        .map(sessionMapper::toDomain)

    override suspend fun getAllSessionListByGameId(gameId: Long): List<SessionModel> = sessionDao
        .getAllSessionListByGameId(gameId = gameId)
        .map(sessionMapper::toDomain)

    override suspend fun getLastSessionList(number: Int): List<SessionModel> = sessionDao
        .getLastSessionList(number = number)
        .map(sessionMapper::toDomain)

    override suspend fun getSessionById(sessionId: Long): SessionModel = sessionDao
        .getSessionById(sessionId = sessionId)
        .let(sessionMapper::toDomain)

    override suspend fun deleteBySessionId(sessionId: Long) = sessionDao
        .delete(
            sessionLocalDb = sessionDao.getSessionById(sessionId = sessionId),
        )

}
