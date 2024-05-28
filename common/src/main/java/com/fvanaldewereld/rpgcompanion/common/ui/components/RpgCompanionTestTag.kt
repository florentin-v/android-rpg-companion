package com.fvanaldewereld.rpgcompanion.common.ui.components

abstract class RpgCompanionTestTag(private val base: String, private val prefix: String, private val suffix: String) {

    val value: String
        get() {
            return buildString {
                if (prefix.isNotEmpty()) append(prefix + "_")
                append(base)
                if (suffix.isNotEmpty()) append("_$suffix")
            }
        }
}
