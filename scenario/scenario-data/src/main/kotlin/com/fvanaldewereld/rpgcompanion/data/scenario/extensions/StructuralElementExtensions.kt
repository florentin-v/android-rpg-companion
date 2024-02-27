package com.fvanaldewereld.rpgcompanion.data.scenario.extensions

import com.google.api.services.docs.v1.model.StructuralElement

fun StructuralElement.getText(): String {
    var text = ""
    this.paragraph?.elements?.forEach { element ->
        element?.textRun?.content?.let { elementText -> text += elementText }
    }
    return text.replace("\n", "").trim()
}
