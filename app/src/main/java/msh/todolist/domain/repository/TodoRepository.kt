package msh.todolist.domain.repository

import msh.todolist.data.local.TodoDao
import msh.todolist.data.local.TodoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(private val dao: TodoDao) : ITodoRepository {
    override fun getAll(): Flow<List<TodoEntity>> = dao.getAll()

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