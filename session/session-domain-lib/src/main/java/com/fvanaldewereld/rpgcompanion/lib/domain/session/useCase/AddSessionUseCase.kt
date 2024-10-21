package com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import com.fvanaldewereld.rpgcompanion.api.domain.session.repository.DbSessionRepository

class AddSessionUseCase(
    private val localDbSessionRepository: DbSessionRepository,
) {

    suspend operator fun invoke(sessionModel: SessionModel) =
        localDbSessionRepository.addSession(sessionModel = sessionModel)
}
