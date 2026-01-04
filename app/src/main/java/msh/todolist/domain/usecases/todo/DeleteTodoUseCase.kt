package msh.todolist.domain.usecases.todo

import javax.inject.Inject
import msh.todolist.domain.repository.ITodoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import msh.todolist.di.IoDispatcher

class DeleteTodoUseCase @Inject constructor(
    private val repository: ITodoRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(scope: CoroutineScope, todoId: Long): Job =
        scope.launch(dispatcher) {
            repository.delete(todoId)
        }
}
