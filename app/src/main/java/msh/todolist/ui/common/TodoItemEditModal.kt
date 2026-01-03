package msh.todolist.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoItemEditModal(
    visible: Boolean,
    initialTitle: String = "",
    initialDescription: String = "",
    onDismiss: () -> Unit,
    onSave: (title: String, description: String) -> Unit
) {
    if (!visible) return

    val titleState = remember { mutableStateOf(initialTitle) }
    val descriptionState = remember { mutableStateOf(initialDescription) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Fila 1: Título
                OutlinedTextField(
                    value = titleState.value,
                    onValueChange = { titleState.value = it },
                    label = { Text("Título") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Fila 2: Descripción
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
                Divider()

                Spacer(modifier = Modifier.height(8.dp))

                // Fila 3: Botones
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = {
                            onSave(titleState.value.trim(), descriptionState.value.trim())
                        }
                    ) {
                        Text("Aceptar")
                    }
                }
            }
        }
    }
}