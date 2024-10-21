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

class GetLastSessionListUseCaseTest : BasicKoinTest() {

    private val mockDbSessionRepository by inject<DbSessionRepository>()

    private lateinit var getLastSessionListUseCase: GetLastSessionListUseCase

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DbSessionRepository>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        getLastSessionListUseCase = GetLastSessionListUseCase(
            localDbSessionRepository = mockDbSessionRepository,
        )
    }

    @Test
    fun `WHEN call getLastSessionListUseCase THEN getLastSessionList from mockDbSessionRepository is called with right param`() =
        runBlocking {
            // WHEN
            val number = 1
            whenever(mockDbSessionRepository.getLastSessionList(number = number))
                .thenReturn(listOf(mock<SessionModel>()))

            // WHEN
            getLastSessionListUseCase(number = number)

            // THEN
            verifyBlocking(mockDbSessionRepository) {
                getLastSessionList(number = number)
            }
        }
}
