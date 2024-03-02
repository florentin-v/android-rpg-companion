package com.fvanaldewereld.rpgcompanion.data.scenario.sources.localDatabase.embedded

data class Information(
    val genres: List<String>? = null,
    val nbPlayers: Int? = null,
    val themes: List<String>? = null,
) : LocalDbEmbedded
