package msh.todolist.domain.usecases.todo

import javax.inject.Inject
import msh.todolist.domain.repository.TodoRepository
import msh.todolist.domain.model.Todo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import msh.todolist.di.IoDispatcher

class InsertTodoUseCase @Inject constructor(
    private val repository: TodoRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(scope: CoroutineScope, todo: Todo): Job =
        scope.launch(dispatcher) {
            repository.insert(todo)
        }
}
