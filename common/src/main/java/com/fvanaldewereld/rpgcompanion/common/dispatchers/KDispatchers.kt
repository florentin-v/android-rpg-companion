package com.fvanaldewereld.rpgcompanion.common.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface KDispatchers {

    fun default(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    fun main(): CoroutineDispatcher
}
