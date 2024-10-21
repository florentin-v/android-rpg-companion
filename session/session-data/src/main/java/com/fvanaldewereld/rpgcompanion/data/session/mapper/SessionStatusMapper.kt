package com.fvanaldewereld.rpgcompanion.data.session.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionStatus as DomainSessionStatus
import com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity.SessionStatus as DataSessionStatus

internal class SessionStatusMapper {

    fun toDomain(from: DataSessionStatus) = DomainSessionStatus.entries.find {
        it.name == from.name
    } ?: DomainSessionStatus.default

    fun toDataLocalDb(from: DomainSessionStatus) = DataSessionStatus.entries.find {
        it.name == from.name
    } ?: DataSessionStatus.default
}
