package com.fvanaldewereld.rpgcompanion.ui.scenario.detail.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ChapterListModel
import com.fvanaldewereld.rpgcompanion.common.ui.theme.Typography

@Composable
fun ScenarioDetailChapters(chapterListModel: ChapterListModel, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                "Content",
                style = Typography.headlineSmall,
                modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
            )
            chapterListModel.list.forEach { chapter ->
                Text(
                    chapter.name,
                    style = Typography.titleLarge,
                    modifier = Modifier.padding(top = 10.dp, bottom = 5.dp),
                )
                chapter.description.paragraphs.forEach { paragraph ->
                    Text(
                        paragraph,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                    )
                }
            }
        }
    }
}
