package msh.todolist.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import msh.todolist.data.local.TodoDao
import msh.todolist.data.local.toDomain
import msh.todolist.data.local.toEntity
import msh.todolist.domain.model.Todo
import msh.todolist.domain.repository.ITodoRepository
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val dao: TodoDao
) : ITodoRepository {
    override fun getAll(): Flow<List<Todo>> = dao.getAll().map { list -> list.map { it.toDomain() } }

    override suspend fun insert(todo: Todo): Long {
        return dao.insert(todo.toEntity())
    }

    override suspend fun update(todo: Todo) {
        dao.update(todo.toEntity())
    }

    override suspend fun delete(todoId: Long) {
        dao.delete(todoId)
    }
}

