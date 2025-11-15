package msh.todolist.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Layout(
    title: String,
    onMenuClick: () -> Unit,
    onItemSelected: (Int) -> Unit,
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = { TopBar(title = title) },
        bottomBar = { BottomBar(onItemSelected = onItemSelected) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LayoutPreview() {
    Layout(
        title = "ToDo List",
        onMenuClick = {},
        onItemSelected = {}
    ) {
        Text("Contenido de la pantalla")
    }
}
