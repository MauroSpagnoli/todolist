// kotlin
package msh.todolist.ui.components.todolist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import msh.todolist.R

data class UiTodo(
    val id: Long,
    val title: String,
    val description: String,
    val completed: Boolean = false
)

@Suppress("FunctionName")
fun LazyListScope.TodoSection(
    titleRes: Int,
    items: List<UiTodo>,
    onAddClick: (Boolean) -> Unit = {},
    onEdit: (UiTodo) -> Unit = { _ -> },
    onDelete: (UiTodo) -> Unit = { _ -> },
    onToggle: (UiTodo, Boolean) -> Unit = { _, _ -> },
    onSave: (UiTodo, String, String) -> Unit = { _, _, _ -> }
) {
    item {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(titleRes),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = { onAddClick(items.firstOrNull()?.completed ?: false) }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = null
                        )
                    }
                }

                if (items.isEmpty()) {
                    Text(
                        text = if (titleRes == R.string.pendientes) "No hay tareas pendientes" else "No hay tareas completadas",
                        style = MaterialTheme.typography.bodyMedium
                    )
                } else {
                    items.forEachIndexed { index, item ->
                        key(item.id) {
                            TodoListItemStateful(
                                item = item,
                                onEdit = onEdit,
                                onDelete = onDelete,
                                onCheckedChange = onToggle,
                                onSave = onSave
                            )
                        }
                        if (index < items.lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 6.dp),
                                thickness = DividerDefaults.Thickness,
                                color = DividerDefaults.color
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TodoList(
    items: List<UiTodo>,
    modifier: Modifier = Modifier,
    onAddClick: (Boolean) -> Unit = {},
    onEdit: (UiTodo) -> Unit = { _ -> },
    onDelete: (UiTodo) -> Unit = { _ -> },
    onToggle: (UiTodo, Boolean) -> Unit = { _, _ -> },
    onSave: (UiTodo, String, String) -> Unit = { _, _, _ -> }
) {
    val pending = items.filter { !it.completed }
    val done = items.filter { it.completed }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        TodoSection(
            titleRes = R.string.pendientes,
            items = pending,
            onAddClick = onAddClick,
            onEdit = onEdit,
            onDelete = onDelete,
            onToggle = onToggle,
            onSave = onSave
        )

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        TodoSection(
            titleRes = R.string.completados,
            items = done,
            onAddClick = onAddClick,
            onEdit = onEdit,
            onDelete = onDelete,
            onToggle = onToggle,
            onSave = onSave
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListPreview() {
    val sample = listOf(
        UiTodo(1, "Comprar leche", "Ir al supermercado y comprar leche, pan y huevos", completed = false),
        UiTodo(2, "Enviar email", "Responder al correo de la reunión", completed = true),
        UiTodo(3, "Ejercicio", "30 minutos de running", completed = false)
    )

    TodoList(items = sample, onToggle = { _, _ -> })
}