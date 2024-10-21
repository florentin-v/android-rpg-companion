package com.fvanaldewereld.rpgcompanion.api.domain.session.model

enum class SessionStatus {
    NOT_STARTED,
    PENDING,
    FINISHED,
    ;

    companion object {
        val default = NOT_STARTED
    }
}
