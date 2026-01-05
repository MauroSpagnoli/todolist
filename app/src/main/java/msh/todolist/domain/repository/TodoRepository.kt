// kotlin
package msh.todolist.domain.repository

import msh.todolist.domain.model.Todo
import kotlinx.coroutines.flow.Flow

// Renombrada la interfaz de ITodoRepository a TodoRepository (estilo idiomático Kotlin)
interface TodoRepository {
    fun getAll(): Flow<List<Todo>>
    suspend fun insert(todo: Todo): Long
    suspend fun update(todo: Todo)
    suspend fun delete(todoId: Long)
}