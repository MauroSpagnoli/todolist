package msh.todolist.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onTaskSelected: () -> Unit,
    onSettingsSelected: () -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    NavigationBar(modifier = modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = selectedIndex == 1,
            onClick = {
                selectedIndex = 1
                onTaskSelected()
            },
            icon = { Icon(imageVector = Icons.Filled.List, contentDescription = "Tareas") },
            label = { Text("Tareas") }
        )

        NavigationBarItem(
            selected = selectedIndex == 2,
            onClick = {
                selectedIndex = 2
                onSettingsSelected()
            },
            icon = { Icon(imageVector = Icons.Filled.Settings, contentDescription = "Ajustes") },
            label = { Text("Ajustes") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomBar(
        modifier = Modifier,
        onTaskSelected = {},
        onSettingsSelected = {}
    )
}