package com.fvanaldewereld.rpgcompanion.data.session.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionStatus as SessionStatusDomain
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionStatus as SessionStatusData

internal class SessionStatusMapper {

    fun toDomain(from: SessionStatusData) = SessionStatusDomain.entries.find {
        it.name == from.name
    } ?: SessionStatusDomain.default

    fun toDataLocalDb(from: SessionStatusDomain) = SessionStatusData.entries.find {
        it.name == from.name
    } ?: SessionStatusData.default
}
