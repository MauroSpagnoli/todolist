package msh.todolist.ui.components.todolist

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import msh.todolist.ui.components.common.Layout

@Composable
fun ListScreen(
    onSettings: () -> Unit,
    todos: List<UiTodo>,
    onAdd: suspend (title: String, description: String?, completed: Boolean) -> Unit,
    onDelete: (id: Long) -> Unit,
    onToggle: (id: Long, completed: Boolean) -> Unit,
    onSave: (id: Long, title: String, description: String?) -> Unit
) {
    val isAddVisible = remember { mutableStateOf(false) }
    val isAddCompleted = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Layout(
        title = "ToDo List",
        onTaskSelected = {},
        onSettingsSelected = { onSettings() },
        snackbarHostState = snackbarHostState
    ) {
        TodoList(
            items = todos,
            onAddClick = { completed ->
                isAddCompleted.value = completed
                isAddVisible.value = true
            },
            onDelete = { item -> onDelete(item.id) },
            onToggle = { item, checked -> onToggle(item.id, checked) },
            onSave = { item, newTitle, newDescription ->
                onSave(
                    item.id,
                    newTitle,
                    newDescription.ifEmpty { null })
            }
        )

        // Modal para agregar
        TodoItemAddModal(
            visible = isAddVisible.value,
            initialCompleted = isAddCompleted.value,
            forceCompleted = isAddCompleted.value,
            onDismiss = { isAddVisible.value = false },
            onAdd = { title, description, completed ->
                if (title.isNotBlank()) {
                    coroutineScope.launch {
                        try {
                            onAdd(title, description.ifBlank { null }, completed)
                            snackbarHostState.showSnackbar("Tarea añadida")
                        } catch (t: Throwable) {
                            snackbarHostState.showSnackbar("Error al añadir la tarea: ${t.message ?: ""}")
                        }
                    }
                }
                isAddVisible.value = false
            }
        )
    }
}
