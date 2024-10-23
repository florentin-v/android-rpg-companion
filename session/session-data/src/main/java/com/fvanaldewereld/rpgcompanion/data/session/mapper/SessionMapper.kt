package com.fvanaldewereld.rpgcompanion.data.session.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionLocalDb

internal class SessionMapper(
    val sessionStatusMapper: SessionStatusMapper,
) {

    fun toDomain(from: SessionLocalDb) = SessionModel(
        id = from.id,
        title = from.title,
        status = from.status.let(sessionStatusMapper::toDomain),
        gameId = from.gameId,
    )

    fun toDataLocalDb(from: SessionModel) = SessionLocalDb(
        id = from.id,
        title = from.title,
        status = from.status.let(sessionStatusMapper::toDataLocalDb),
        gameId = from.gameId,
    )
}
