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

data class TodoItem(
    val id: Long,
    var title: String,
    var description: String,
    var completed: Boolean = false,
    val onEdit: () -> Unit = {},
    val onDelete: () -> Unit = {}
)

@Suppress("FunctionName")
fun LazyListScope.TodoPendingSection(
    items: List<TodoItem>,
    onEdit: (TodoItem) -> Unit = {},
    onDelete: (TodoItem) -> Unit = {},
    onToggle: (TodoItem) -> Unit = {},
    onSave: (TodoItem, String, String) -> Unit = { _, _, _ -> }
) {
    if (items.isNotEmpty()) {
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
                            text = stringResource(R.string.pendientes),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = null
                            )
                        }
                    }

                    items.forEachIndexed { index, item ->
                        key(item.id) {
                        TodoListItemStateful(
                            title = item.title,
                            description = item.description,
                            initialCompleted = item.completed,
                            onEdit = { onEdit(item) },
                            onDelete = { onDelete(item) },
                            onCheckedChange = { checked -> onToggle(item.copy(completed = checked)) },
                            onSave = { newTitle, newDescription -> onSave(item, newTitle, newDescription) }
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

@Suppress("FunctionName")
fun LazyListScope.TodoCompletedSection(
    items: List<TodoItem>,
    onEdit: (TodoItem) -> Unit = {},
    onDelete: (TodoItem) -> Unit = {},
    onToggle: (TodoItem) -> Unit = {},
    onSave: (TodoItem, String, String) -> Unit = { _, _, _ -> }
) {
    if (items.isNotEmpty()) {
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
                            text = stringResource(R.string.completados),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = null
                            )
                        }
                    }

                    items.forEachIndexed { index, item ->
                        key(item.id) {
                        TodoListItemStateful(
                            title = item.title,
                            description = item.description,
                            initialCompleted = item.completed,
                            onEdit = { onEdit(item) },
                            onDelete = { onDelete(item) },
                            onCheckedChange = { checked -> onToggle(item.copy(completed = checked)) },
                            onSave = { newTitle, newDescription -> onSave(item, newTitle, newDescription) }
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
    items: List<TodoItem>,
    modifier: Modifier = Modifier,
    onEdit: (TodoItem) -> Unit = {},
    onDelete: (TodoItem) -> Unit = {},
    onToggle: (TodoItem) -> Unit = {},
    onSave: (TodoItem, String, String) -> Unit = { _, _, _ -> }
) {
    val pending = items.filter { !it.completed }
    val done = items.filter { it.completed }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        TodoPendingSection(pending, onEdit = onEdit, onDelete = onDelete, onToggle = onToggle, onSave = onSave)

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        TodoCompletedSection(done, onEdit = onEdit, onDelete = onDelete, onToggle = onToggle, onSave = onSave)
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListPreview() {
    val sample = listOf(
        TodoItem(
            1,
            "Comprar leche",
            "Ir al supermercado y comprar leche, pan y huevos",
            completed = false
        ),
        TodoItem(2, "Enviar email", "Responder al correo de la reunión", completed = true),
        TodoItem(3, "Ejercicio", "30 minutos de running", completed = false)
    )

    TodoList(items = sample, onToggle = { /* actualizar estado en ViewModel o padre */ })
}