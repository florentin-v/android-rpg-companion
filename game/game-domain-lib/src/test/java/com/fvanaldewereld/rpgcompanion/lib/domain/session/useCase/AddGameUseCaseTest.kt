package com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.api.domain.game.repository.DbGameRepository
import com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase.AddGameUseCase
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

class AddGameUseCaseTest : BasicKoinTest() {

    private val mockDbGameRepository by inject<DbGameRepository>()

    private lateinit var addGameUseCase: AddGameUseCase

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DbGameRepository>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        addGameUseCase = AddGameUseCase(
            localDbGameRepository = mockDbGameRepository,
        )
    }

    @Test
    fun `WHEN call addGameUseCase THEN addGame from mockDbGameRepository is called with right param`() =
        runBlocking {
            // WHEN
            val gameModel = mock<GameModel>()
            whenever(mockDbGameRepository.addGame(gameModel = gameModel))
                .then {}

            // WHEN
            addGameUseCase(gameModel = gameModel)

            // THEN
            verifyBlocking(mockDbGameRepository) {
                addGame(gameModel = gameModel)
            }
        }
}
