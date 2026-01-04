package msh.todolist.ui.components.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import msh.todolist.ui.components.common.Layout

@Composable
fun SettingsScreen(
    onTaskSelected: () -> Unit,
    onSettingsSelected: () -> Unit
) {
    Layout(
        title = "Ajustes",
        onTaskSelected = onTaskSelected,
        onSettingsSelected = onSettingsSelected,
    ) {
        Column(modifier = Modifier) {
            Text("Pantalla de ajustes (pendiente de implementación)")
        }
    }
}