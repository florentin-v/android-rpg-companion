package com.fvanaldewereld.rpgcompanion.data.character.mappers

import com.fvanaldewereld.rpgcompanion.api.domain.character.models.Model
import com.fvanaldewereld.rpgcompanion.data.character.sources.localDatabase.LocalDbObject

interface DbObjectMapper<FROM : LocalDbObject?, TO : Model?> {

    fun to(from: FROM): TO

    fun from(to: TO): FROM
}
