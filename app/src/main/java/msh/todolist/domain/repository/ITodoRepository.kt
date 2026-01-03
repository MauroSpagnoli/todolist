// kotlin
package msh.todolist.domain.repository

import msh.todolist.data.local.TodoEntity

interface ITodoRepository {
    suspend fun getAll(): List<TodoEntity>
    suspend fun insert(todo: TodoEntity)
    suspend fun update(todo: TodoEntity)
    suspend fun delete(todoId: Long)
}