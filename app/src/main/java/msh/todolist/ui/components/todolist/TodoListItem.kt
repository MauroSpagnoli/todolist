// kotlin
package msh.todolist.ui.components.todolist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import msh.todolist.R

@Composable
fun TodoListItemStateful(
    item: UiTodo,
    onEdit: (UiTodo) -> Unit = {},
    onDelete: (UiTodo) -> Unit = {},
    onCheckedChange: (UiTodo, Boolean) -> Unit = { _, _ -> },
    onSave: (UiTodo, String, String) -> Unit = { _, _, _ -> }
) {
    var completed by remember { mutableStateOf(item.completed) }
    var menuExpanded by remember { mutableStateOf(false) }
    var isEditModalVisible by remember { mutableStateOf(false) }

    LaunchedEffect(item.completed) {
        completed = item.completed
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            textDecoration = if (completed) TextDecoration.LineThrough else TextDecoration.None,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f),
                textDecoration = if (completed) TextDecoration.LineThrough else TextDecoration.None,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { menuExpanded = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.mas_opciones)
                    )
                }

                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.editar)) },
                        leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) },
                        onClick = {
                            menuExpanded = false
                            isEditModalVisible = true
                            onEdit(item)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(stringResource(R.string.eliminar)) },
                        leadingIcon = { Icon(Icons.Default.Delete, contentDescription = null) },
                        onClick = {
                            menuExpanded = false
                            onDelete(item)
                        }
                    )
                }

                Checkbox(
                    checked = completed,
                    onCheckedChange = { checked ->
                        completed = checked
                        onCheckedChange(item, checked)
                    }
                )
            }
        }
    }

    TodoItemEditModal(
        visible = isEditModalVisible,
        initialTitle = item.title,
        initialDescription = item.description,
        onDismiss = { isEditModalVisible = false },
        onSave = { newTitle, newDescription ->
            isEditModalVisible = false
            onSave(item, newTitle, newDescription)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TodoListItemPreview() {
    TodoListItemStateful(
        item = UiTodo(1, "Comprar leche", "Ir al supermercado y comprar leche, pan y huevos", completed = false),
        onEdit = {},
        onDelete = {}
    )
}