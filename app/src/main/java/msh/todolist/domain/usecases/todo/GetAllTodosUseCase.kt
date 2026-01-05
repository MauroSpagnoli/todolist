package msh.todolist.domain.usecases.todo

import kotlinx.coroutines.flow.Flow
import msh.todolist.domain.model.Todo
import msh.todolist.domain.repository.TodoRepository
import javax.inject.Inject

class GetAllTodosUseCase @Inject constructor(
    private val repository: TodoRepository,
) {
    operator fun invoke(): Flow<List<Todo>> = repository.getAll()
}
