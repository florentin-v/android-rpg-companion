package com.fvanaldewereld.rpgcompanion.ui.scenario.list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fvanaldewereld.rpgcompanion.api.domain.scenario.models.ScenarioModel
import com.fvanaldewereld.rpgcompanion.common.ui.components.RpgCompanionTopAppBar
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.R
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.ScenarioListUiState
import com.fvanaldewereld.rpgcompanion.ui.scenario.list.model.ScenarioListScreenAction
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScenarioListScreen(
    uiState: ScenarioListUiState,
    modifier: Modifier = Modifier,
    onScenarioListScreenAction: (ScenarioListScreenAction) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    var deletingScenario by remember { mutableStateOf<ScenarioModel?>(null) }

    Scaffold(
        modifier = modifier,
        topBar = {
            RpgCompanionTopAppBar(
                title = stringResource(R.string.scenarioList_topBar_title),
                onBackButtonPressed = { onScenarioListScreenAction(ScenarioListScreenAction.OnBackPressedButton) },
            )
        },
        floatingActionButton = {
            ScenarioListAddButton(onClick = { showBottomSheet = true })
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            when (uiState) {
                is ScenarioListUiState.Error -> ScenarioListError(errorMessage = uiState.errorMessage)
                is ScenarioListUiState.Loading -> ScenarioListLoading()
                is ScenarioListUiState.NoResult -> ScenarioListNoResult()
                is ScenarioListUiState.Success -> ScenarioListSuccess(
                    scenarioListModel = uiState.scenarioListModel,
                    updateDeletingScenario = { scenarioModel ->
                        openDialog = true
                        deletingScenario = scenarioModel
                    },
                    onScenarioListScreenAction = onScenarioListScreenAction,
                )
            }
        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
        ) {
            ScenarioListBottomSheet(
                hideBottomSheet = {
                    scope
                        .launch {
                            sheetState.hide()
                        }
                        .invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                },
                onScenarioListScreenAction = onScenarioListScreenAction,
            )
        }
    }
    if (openDialog) {
        ScenarioItemDeletingDialog(
            scenario = deletingScenario,
            onConfirmation = { scenarioId ->
                openDialog = false
                onScenarioListScreenAction(
                    ScenarioListScreenAction.DeleteScenario(id = scenarioId),
                )
            },
            onCancel = { openDialog = false },
        )
    }
}
