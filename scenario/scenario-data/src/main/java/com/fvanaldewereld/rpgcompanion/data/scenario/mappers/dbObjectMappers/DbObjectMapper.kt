package com.fvanaldewereld.rpgcompanion.data.scenario.mappers.dbObjectMappers

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.Model
import com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.LocalDbObject

interface DbObjectMapper<FROM : LocalDbObject?, TO : Model?> {

    fun to(from: FROM): TO

    fun from(to: TO): FROM
}
