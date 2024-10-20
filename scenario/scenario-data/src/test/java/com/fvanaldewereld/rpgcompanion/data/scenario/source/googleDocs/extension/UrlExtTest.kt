package com.fvanaldewereld.rpgcompanion.data.scenario.source.googleDocs.extension

import com.fvanaldewereld.rpgcompanion.mockFactory.GoogleDocsMockFactory
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class UrlExtTest {

    @Test
    fun `GIVEN URL WHEN extractDocumentId THEN return the documentId`() {
        // GIVEN

        // WHEN
        val documentId = GoogleDocsMockFactory.googleDocsUrl.extractGoogleDocumentId()

        // THEN
        assertEquals(
            actual = documentId,
            expected = GoogleDocsMockFactory.GOOGLE_DOCS_DOCUMENT_ID,
        )
    }
}
