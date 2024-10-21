package com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.api.domain.game.repository.DbGameRepository
import com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase.GetGameByIdUseCase
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

class GetGameByIdUseCaseTest : BasicKoinTest() {

    private val mockDbGameRepository by inject<DbGameRepository>()

    private lateinit var getGameByIdUseCase: GetGameByIdUseCase

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DbGameRepository>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        getGameByIdUseCase = GetGameByIdUseCase(
            localDbGameRepository = mockDbGameRepository,
        )
    }

    @Test
    fun `WHEN call getGameByIdUseCase THEN getGameById from mockDbGameRepository is called with right param`() =
        runBlocking {
            // WHEN
            val gameId = 1L
            whenever(mockDbGameRepository.getGameById(gameId = gameId))
                .thenReturn(mock<GameModel>())

            // WHEN
            getGameByIdUseCase(gameId = gameId)

            // THEN
            verifyBlocking(mockDbGameRepository) {
                getGameById(gameId = gameId)
            }
        }
}
