package com.fvanaldewereld.rpgcompanion.data.session.source.localDb.entity

enum class SessionStatus {
    NOT_STARTED,
    PENDING,
    FINISHED;

    companion object {
        val default = NOT_STARTED
    }
}
