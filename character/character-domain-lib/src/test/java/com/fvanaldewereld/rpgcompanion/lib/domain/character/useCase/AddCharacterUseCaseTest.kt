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

class AddCharacterUseCaseTest : BasicKoinTest() {

    private val mockDbCharacterRepository by inject<DbCharacterRepository>()

    private lateinit var addCharacterUseCase: AddCharacterUseCase

    override fun KoinApplication.buildModules() {
        modules(
            module {
                single { mock<DbCharacterRepository>() }
            },
        )
    }

    @BeforeEach
    fun setUp() {
        addCharacterUseCase = AddCharacterUseCase(
            localDbCharacterRepository = mockDbCharacterRepository,
        )
    }

    @Test
    fun `WHEN call addCharacterUseCase THEN addCharacter from mockDbCharacterRepository is called with right param`() =
        runBlocking {
            // WHEN
            val characterModel = mock<CharacterModel>()
            whenever(mockDbCharacterRepository.addCharacter(characterModel = characterModel))
                .then {}

            // WHEN
            addCharacterUseCase(characterModel = characterModel)

            // THEN
            verifyBlocking(mockDbCharacterRepository) {
                addCharacter(characterModel = characterModel)
            }
        }
}
