package msh.todolist.ui.components.todolist

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import msh.todolist.domain.model.Todo
import msh.todolist.ui.components.common.Layout
import msh.todolist.ui.viewmodel.TodoListViewModel

@Composable
fun ListScreen(
    onSettings: () -> Unit,
    viewModel: TodoListViewModel
) {
    val todosState by viewModel.todos.collectAsState()
    val isAddVisible = remember { mutableStateOf(false) }
    val isAddCompleted = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val items = todosState.map { entity ->
        TodoItem(
            id = entity.id,
            title = entity.title,
            description = entity.description ?: "",
            completed = entity.completed
        )
    }

    Layout(
        title = "ToDo List",
        onTaskSelected = {},
        onSettingsSelected = { onSettings() },
        snackbarHostState = snackbarHostState
    ) {
        TodoList(
            items = items,
            onAddClick = { completed ->
                isAddCompleted.value = completed
                isAddVisible.value = true
            },
            onDelete = { item ->
                viewModel.deleteTodo(item.id)
            },
            onToggle = { updatedItem ->
                // actualizar completed
                val updated = Todo(
                    id = updatedItem.id,
                    title = updatedItem.title,
                    description = updatedItem.description.ifEmpty { null },
                    completed = updatedItem.completed
                )
                viewModel.updateTodo(updated)
            },
            onSave = { item, newTitle, newDescription ->
                val updated = Todo(
                    id = item.id,
                    title = newTitle,
                    description = newDescription.ifEmpty { null },
                    completed = item.completed
                )
                viewModel.updateTodo(updated)
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
                    val job = viewModel.addTodo(title, description.ifBlank { null }, completed)
                    // esperar a que termine y mostrar snackbar
                    coroutineScope.launch {
                        try {
                            job.join()
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
