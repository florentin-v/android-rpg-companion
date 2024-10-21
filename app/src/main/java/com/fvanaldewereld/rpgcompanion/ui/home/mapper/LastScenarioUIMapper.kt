package com.fvanaldewereld.rpgcompanion.ui.home.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.ui.home.model.LastScenarioUI

class LastScenarioUIMapper {
    fun toUI(from: ScenarioModel?) = from?.id?.let {
        LastScenarioUI(
            id = it,
            author = from.mainInfo.author.name ?: "NO AUTHOR",
            title = from.mainInfo.title.value ?: "NO TITLE",
        )
    }
}
