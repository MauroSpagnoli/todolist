package msh.todolist.ui.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String
) {
    TopAppBar(
        title = { Text(text = title, style = MaterialTheme.typography.titleLarge) }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar("ToDo List")
}