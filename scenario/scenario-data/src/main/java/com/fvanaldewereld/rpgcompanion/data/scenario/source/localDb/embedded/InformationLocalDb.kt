package com.fvanaldewereld.rpgcompanion.data.scenario.source.localDb.embedded

data class InformationLocalDb(
    val genreList: List<String> = emptyList(),
    val nbPlayers: Int = 1,
    val themeList: List<String> = emptyList(),
)
