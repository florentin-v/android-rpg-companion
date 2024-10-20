package com.fvanaldewereld.rpgcompanion.api.domain.session.repository

import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel


interface DbSessionRepository {

    suspend fun addSession(sessionModel: SessionModel)

    suspend fun getAllSessionList(): List<SessionModel>

    suspend fun getAllSessionListByGameId(gameId: Long): List<SessionModel>

    suspend fun getLastSessionList(number: Int): List<SessionModel>

    suspend fun getSessionById(sessionId: Long): SessionModel

    suspend fun deleteBySessionId(sessionId: Long)
}
