package com.fvanaldewereld.rpgcompanion.data.scenario.sources.googleDocs.extensions

import com.fvanaldewereld.rpgcompanion.mockFactory.GoogleDocsMockFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UrlExtensionTest {

    @Test
    fun `GIVEN URL WHEN extractDocumentId THEN return the documentId`() {
        // GIVEN

        // WHEN
        val documentId = GoogleDocsMockFactory.googleDocsUrl.extractGoogleDocumentId()

        // THEN
        Assertions.assertEquals(documentId, GoogleDocsMockFactory.GOOGLE_DOCS_DOCUMENT_ID)
    }
}
