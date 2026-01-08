package msh.todolist.ui.components.todolist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItemAddModal(
    visible: Boolean,
    initialTitle: String = "",
    initialDescription: String = "",
    initialCompleted: Boolean = false,
    forceCompleted: Boolean = false,
    onDismiss: () -> Unit,
    onAdd: (title: String, description: String, completed: Boolean) -> Unit
) {
    if (!visible) return

    val titleState = remember { mutableStateOf(initialTitle) }
    val descriptionState = remember { mutableStateOf(initialDescription) }
    val completedState = remember { mutableStateOf(initialCompleted) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = titleState.value,
                    onValueChange = { titleState.value = it },
                    label = { Text("Título") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = descriptionState.value,
                    onValueChange = { descriptionState.value = it },
                    label = { Text("Descripción") },
                    singleLine = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = {
                            val completed = if (forceCompleted) true else completedState.value
                            onAdd(titleState.value.trim(), descriptionState.value.trim(), completed)
                        }
                    ) {
                        Text("Agregar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoItemAddModalPreview() {
    TodoItemAddModal(
        visible = true,
        initialTitle = "",
        initialDescription = "",
        initialCompleted = false,
        forceCompleted = false,
        onDismiss = {},
        onAdd = { _, _, _ -> }
    )
}
