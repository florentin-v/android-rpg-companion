package com.fvanaldewereld.rpgcompanion.lib.domain.character.useCase

import com.fvanaldewereld.rpgcompanion.api.domain.character.model.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.character.repository.DbCharacterRepository
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

class GetLastCharacterListUseCaseTest : BasicKoinTest() {

    private val mockDbCharacterRepository by inject<DbCharacterRepository>()

    private lateinit var getLastCharacterListUseCase: GetLastCharacterListUseCase

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DbCharacterRepository>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        getLastCharacterListUseCase = GetLastCharacterListUseCase(
            localDbCharacterRepository = mockDbCharacterRepository,
        )
    }

    @Test
    fun `WHEN call getLastCharacterListUseCase THEN getLastCharacterList from mockDbCharacterRepository is called with right param`() =
        runBlocking {
            // WHEN
            val number = 1
            whenever(mockDbCharacterRepository.getLastCharacterList(number = number))
                .thenReturn(listOf(mock<CharacterModel>()))

            // WHEN
            getLastCharacterListUseCase(number = number)

            // THEN
            verifyBlocking(mockDbCharacterRepository) {
                getLastCharacterList(number = number)
            }
        }
}
