package com.fvanaldewereld.rpgcompanion.data.scenario.source.googleDocs.service

import android.content.Context
import com.fvanaldewereld.rpgcompanion.common.R
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.HttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.docs.v1.Docs
import com.google.api.services.docs.v1.DocsScopes
import java.io.InputStream

class GoogleDocsService(private val context: Context) {

    private val jsonFactory: JsonFactory = GsonFactory()

    private var docs: Docs? = null

    fun getDocs(): Docs {
        if (docs == null) {
            docs = initDocsBuilder()
        }
        return docs
            ?: throw GoogleDocsServiceException(
                message = "An error as occured in GoogleDocsService. Docs should be not null.",
            )
    }

    private fun initDocsBuilder(): Docs {
        val inputStream: InputStream = context.assets.open("service-account-key.json")
        val credentials: GoogleCredential = GoogleCredential.fromStream(inputStream)
            .createScoped(listOf(DocsScopes.DOCUMENTS_READONLY))

        return Docs.Builder(createNetHttpTransport(), jsonFactory, credentials)
            .setApplicationName(context.getString(R.string.app_name))
            .build()
    }

    private fun createNetHttpTransport(): HttpTransport {
        return NetHttpTransport.Builder()
            .build()
    }
}
