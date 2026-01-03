// MainActivity.kt
package msh.todolist.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import msh.todolist.ui.common.Layout
import msh.todolist.ui.common.TodoItem
import msh.todolist.ui.common.TodoItemEditModal
import msh.todolist.ui.common.TodoList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val items = remember {
        mutableStateListOf(
            TodoItem(
                1,
                "Comprar leche",
                "Ir al supermercado y comprar leche, pan y huevos",
                completed = false
            ),
            TodoItem(2, "Enviar email", "Responder al correo de la reunión", completed = true),
            TodoItem(3, "Ejercicio", "30 minutos de running", completed = false),
            TodoItem(
                4,
                "Leer libro",
                "Terminar de leer el capítulo 5 del libro de Compose",
                completed = true
            ),
            TodoItem(
                5,
                "Llamar a mamá",
                "Preguntar cómo está y contarle las novedades",
                completed = false
            ),
            TodoItem(
                6,
                "Pasear al perro",
                "Llevar a Max al parque por la tarde",
                completed = true
            )
        )
    }

    val isModalVisible = remember { mutableStateOf(false) }
    val selectedItem = remember { mutableStateOf<TodoItem?>(null) }

    Layout(
        title = "ToDo List",
        onTaskSelected = {},
        onSettingsSelected = {},
    ) {
        TodoList(
            items = items,
            onEdit = { item ->
                selectedItem.value = item
                isModalVisible.value = true
            },
            onDelete = { item ->
                items.remove(item)
            },
            onToggle = { updatedItem ->
                val index = items.indexOfFirst { it.id == updatedItem.id }
                if (index != -1) {
                    items[index] = items[index].copy(completed = !items[index].completed)
                }
            }
        )

        if (isModalVisible.value && selectedItem.value != null) {
            TodoItemEditModal(
                visible = isModalVisible.value,
                initialTitle = selectedItem.value?.title ?: "",
                initialDescription = selectedItem.value?.description ?: "",
                onDismiss = { isModalVisible.value = false },
                onSave = { title, description ->
                    selectedItem.value?.let { item ->
                        val index = items.indexOfFirst { it.id == item.id }
                        if (index != -1) {
                            items[index] = item.copy(title = title, description = description)
                        }
                    }
                    isModalVisible.value = false
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen()
    }
}