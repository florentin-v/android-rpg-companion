package com.fvanaldewereld.rpgcompanion.data.game.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.data.game.source.localDb.entity.GameLocalDb
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameMapperTest {

    private lateinit var gameMapper: GameMapper

    @BeforeEach
    fun setUp() {
        gameMapper = GameMapper()
    }

    @Test
    fun `WHEN execute toDomain with gameLocalDb THEN return gameModel`() {
        // GIVEN

        // WHEN
        val gameLocalDb = GameLocalDb(
            id = 1,
            name = "Test Game",
        )

        val gameModel = gameMapper.toDomain(gameLocalDb)

        // THEN
        val expectedGameModel = GameModel(
            id = 1,
            name = "Test Game",
        )

        assertEquals(
            actual = gameModel,
            expected = expectedGameModel,
        )
    }

    @Test
    fun `WHEN execute toDataLocalDb with gameModel THEN return gameLocalDb`() {
        // GIVEN

        // WHEN
        val gameModel = GameModel(
            id = 1,
            name = "Test Game",
        )

        val gameLocalDb = gameMapper.toDataLocalDb(gameModel)

        // THEN
        val expectedGameLocalDb = GameLocalDb(
            id = 1,
            name = "Test Game",
        )

        assertEquals(
            actual = gameLocalDb,
            expected = expectedGameLocalDb,
        )
    }
}
