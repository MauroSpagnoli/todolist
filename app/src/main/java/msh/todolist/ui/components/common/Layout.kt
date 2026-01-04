// kotlin
package msh.todolist.ui.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Layout(
    title: String,
    onTaskSelected: () -> Unit,
    onSettingsSelected: () -> Unit,
    snackbarHostState: SnackbarHostState? = null,
    content: @Composable () -> Unit
) {
    val hostState = snackbarHostState ?: remember { SnackbarHostState() }

    Scaffold(
        topBar = { TopBar(title = title) },
        bottomBar = {
            BottomBar(
                modifier = Modifier,
                onTaskSelected = onTaskSelected,
                onSettingsSelected = onSettingsSelected
            )
        },
        snackbarHost = { SnackbarHost(hostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.LightGray)
        ) {
            content()
        }
    }
}