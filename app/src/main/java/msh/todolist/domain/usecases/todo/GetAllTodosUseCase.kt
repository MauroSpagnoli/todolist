package msh.todolist.domain.usecases.todo

import javax.inject.Inject
import msh.todolist.domain.repository.ITodoRepository
import msh.todolist.data.local.TodoEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import msh.todolist.di.IoDispatcher

class GetAllTodosUseCase @Inject constructor(
    private val repository: ITodoRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(): Flow<List<TodoEntity>> = repository.getAll().flowOn(dispatcher)
}
