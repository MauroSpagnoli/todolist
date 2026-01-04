package msh.todolist.ui.activities

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.Text
import msh.todolist.ui.components.common.Layout
import msh.todolist.ui.components.todolist.TodoItem
import msh.todolist.ui.components.todolist.TodoList

@Composable
fun ListScreen(onSettings: () -> Unit) {
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
            TodoItem(6, "Pasear al perro", "Llevar a Max al parque por la tarde", completed = true)
        )
    }

    Layout(
        title = "ToDo List",
        onTaskSelected = {}, // si luego añades detalle, navega desde aquí
        onSettingsSelected = { onSettings() },
    ) {
        TodoList(
            items = items,
            onEdit = { /* ahora el propio ítem abre su modal, este callback puede usarse para analytics u otra lógica */ },
            onDelete = { item ->
                items.remove(item)
            },
            onToggle = { updatedItem ->
                val index = items.indexOfFirst { it.id == updatedItem.id }
                if (index != -1) {
                    // aplicar el estado que viene del item (checked/unchecked)
                    items[index] = items[index].copy(completed = updatedItem.completed)
                }
            },
            onSave = { item, newTitle, newDescription ->
                val index = items.indexOfFirst { it.id == item.id }
                if (index != -1) {
                    items[index] = items[index].copy(title = newTitle, description = newDescription)
                }
            }
        )
    }
}

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

@Preview(showBackground = true)
@Composable
fun ScreensPreview() {
    // Muestra una previsualización rápida del ListScreen dentro del tema
    ListScreen(onSettings = {})
}

