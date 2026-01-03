package msh.todolist.domain.repository

import msh.todolist.data.local.TodoDao
import msh.todolist.data.local.TodoEntity

class TodoRepository(private val dao: TodoDao) : ITodoRepository {
    override suspend fun getAll(): List<TodoEntity> = dao.getAll()

    override suspend fun insert(todo: TodoEntity) {
        dao.insert(todo)
    }

    override suspend fun update(todo: TodoEntity) {
        dao.update(todo)
    }

    override suspend fun delete(todoId: Long) {
        dao.delete(todoId)
    }
}