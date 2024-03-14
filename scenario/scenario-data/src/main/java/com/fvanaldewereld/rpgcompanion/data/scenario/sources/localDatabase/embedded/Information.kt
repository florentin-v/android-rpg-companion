package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.embedded

data class Information(
    val genres: List<String> = emptyList(),
    val nbPlayers: Int = 1,
    val themes: List<String> = emptyList(),
) : LocalDbEmbedded
