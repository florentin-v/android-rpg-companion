package com.fvanaldewereld.rpgcompanion.data.scenario.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.TitleModel
import com.fvanaldewereld.rpgcompanion.data.scenario.dto.TitleDto

internal class TitleMapper {

    /**
     * Converts a [TitleDto] to a [TitleModel].
     *
     * The domain model [TitleModel] is created by mapping the value field of the [TitleDto].
     *
     * @param from The [TitleDto] to convert.
     * @return The converted [TitleModel].
     */
    fun toDomain(from: TitleDto?) = TitleModel(value = from?.value)
}
