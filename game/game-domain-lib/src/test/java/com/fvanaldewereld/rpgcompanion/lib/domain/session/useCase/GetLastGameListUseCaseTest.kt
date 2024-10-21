package com.fvanaldewereld.rpgcompanion.lib.domain.session.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.api.domain.game.repository.DbGameRepository
import com.fvanaldewereld.rpgcompanion.lib.domain.game.useCase.GetLastGameListUseCase
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

class GetLastGameListUseCaseTest : BasicKoinTest() {

    private val mockDbGameRepository by inject<DbGameRepository>()

    private lateinit var getLastGameListUseCase: GetLastGameListUseCase

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DbGameRepository>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        getLastGameListUseCase = GetLastGameListUseCase(
            localDbGameRepository = mockDbGameRepository,
        )
    }

    @Test
    fun `WHEN call getLastGameListUseCase THEN getLastGameList from mockDbGameRepository is called with right param`() =
        runBlocking {
            // WHEN
            val number = 1
            whenever(mockDbGameRepository.getLastGameList(number = number))
                .thenReturn(listOf(mock<GameModel>()))

            // WHEN
            getLastGameListUseCase(number = number)

            // THEN
            verifyBlocking(mockDbGameRepository) {
                getLastGameList(number = number)
            }
        }
}
