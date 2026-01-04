package msh.todolist.domain.usecases.todo

import javax.inject.Inject
import msh.todolist.domain.repository.ITodoRepository
import msh.todolist.data.local.TodoEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import msh.todolist.di.IoDispatcher

class InsertTodoUseCase @Inject constructor(
    private val repository: ITodoRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(scope: CoroutineScope, todo: TodoEntity): Job =
        scope.launch(dispatcher) {
            repository.insert(todo)
        }
}
