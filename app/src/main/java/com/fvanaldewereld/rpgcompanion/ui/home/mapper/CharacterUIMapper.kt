package com.fvanaldewereld.rpgcompanion.ui.home.mapper

import com.fvanaldewereld.rpgcompanion.api.domain.character.model.CharacterModel
import com.fvanaldewereld.rpgcompanion.api.domain.game.model.GameModel
import com.fvanaldewereld.rpgcompanion.ui.home.model.CharacterUI

class CharacterUIMapper {
    fun toUIWithGameModel(from: CharacterModel, gameModel: GameModel?) = CharacterUI(
        id = from.id,
        name = from.name,
        level = from.level,
        game = gameModel ?: GameModel(name = "UNKNOWN GAME"),
    )
}
