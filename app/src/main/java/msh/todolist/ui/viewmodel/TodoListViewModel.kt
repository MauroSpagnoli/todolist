package msh.todolist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import msh.todolist.domain.usecases.todo.GetAllTodosUseCase
import msh.todolist.domain.usecases.todo.InsertTodoUseCase
import msh.todolist.domain.usecases.todo.UpdateTodoUseCase
import msh.todolist.domain.usecases.todo.DeleteTodoUseCase
import msh.todolist.domain.model.Todo
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val insertTodoUseCase: InsertTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {

    val todos: StateFlow<List<Todo>> = getAllTodosUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addTodo(title: String, description: String?, completed: Boolean = false): Job {
        val todo = Todo(title = title, description = description, completed = completed)
        return insertTodoUseCase(viewModelScope, todo)
    }

    fun updateTodo(todo: Todo) {
        updateTodoUseCase(viewModelScope, todo)
    }

    fun deleteTodo(todoId: Long) {
        deleteTodoUseCase(viewModelScope, todoId)
    }
}
