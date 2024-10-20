package com.fvanaldewereld.rpgcompanion.data.scenario.source.googleDocs.extension

import java.net.URL

fun URL.extractGoogleDocumentId(): String {
    val regex = "/d/(.*?)/edit".toRegex()
    val matchResult = regex.find(this.path)
    return matchResult?.groups?.get(1)?.value
        ?: throw NoSuchElementException("No DocumentID in this URL.")
}
