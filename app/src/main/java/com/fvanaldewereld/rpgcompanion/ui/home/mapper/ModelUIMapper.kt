package com.fvanaldewereld.rpgcompanion.ui.home.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.Model
import com.fvanaldewereld.rpgcompanion.ui.home.model.ModelUI

fun interface ModelUIMapper<FROM : Model?, TO : ModelUI?> {

    fun to(from: FROM): TO
}

