// kotlin
package msh.todolist.domain.repository

import msh.todolist.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {
    fun getAll(): Flow<List<Todo>>
    suspend fun insert(todo: Todo): Long
    suspend fun update(todo: Todo)
    suspend fun delete(todoId: Long)
}