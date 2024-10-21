package com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import com.fvanaldewereld.rpgcompanion.api.domain.session.repository.DbSessionRepository

class GetLastSessionListUseCase(
    private val localDbSessionRepository: DbSessionRepository,
) {

    suspend operator fun invoke(number: Int): List<SessionModel> =
        localDbSessionRepository.getLastSessionList(number = number)
}
