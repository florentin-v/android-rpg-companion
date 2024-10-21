package com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.session.model.SessionModel
import com.fvanaldewereld.rpgcompanion.api.domain.session.repository.DbSessionRepository
import com.fvanaldewereld.rpgcompanion.test.common.BasicKoinTest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.KoinApplication
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyBlocking
import org.mockito.kotlin.whenever
import kotlin.getValue

class AddSessionUseCaseTest : BasicKoinTest() {

    private val mockDbSessionRepository by inject<DbSessionRepository>()

    private lateinit var addSessionUseCase: AddSessionUseCase

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DbSessionRepository>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        addSessionUseCase = AddSessionUseCase(
            localDbSessionRepository = mockDbSessionRepository,
        )
    }

    @Test
    fun `WHEN call addSessionUseCase THEN addSession from mockDbSessionRepository is called with right param`() =
        runBlocking {
            // WHEN
            val sessionModel = mock<SessionModel>()
            whenever(mockDbSessionRepository.addSession(sessionModel = sessionModel))
                .then {}

            // WHEN
            addSessionUseCase(sessionModel = sessionModel)

            // THEN
            verifyBlocking(mockDbSessionRepository) {
                addSession(sessionModel = sessionModel)
            }
        }
}
