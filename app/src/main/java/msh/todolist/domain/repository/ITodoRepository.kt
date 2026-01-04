// kotlin
package msh.todolist.domain.repository

import msh.todolist.data.local.TodoEntity
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {
    fun getAll(): Flow<List<TodoEntity>>
    suspend fun insert(todo: TodoEntity)
    suspend fun update(todo: TodoEntity)
    suspend fun delete(todoId: Long)
}