package msh.todolist.viewmodel

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import msh.todolist.domain.model.Todo
import msh.todolist.domain.usecases.todo.DeleteTodoUseCase
import msh.todolist.domain.usecases.todo.GetAllTodosUseCase
import msh.todolist.domain.usecases.todo.InsertTodoUseCase
import msh.todolist.domain.usecases.todo.UpdateTodoUseCase
import msh.todolist.ui.viewmodel.TodoListViewModel
import org.junit.Before
import org.junit.Test

class TodoListViewModelTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `addTodo calls insert use case`() = runTest {
        val getAll = mockk<GetAllTodosUseCase>()
        val insert = mockk<InsertTodoUseCase>()
        val update = mockk<UpdateTodoUseCase>()
        val delete = mockk<DeleteTodoUseCase>()

        every { getAll.invoke() } returns flowOf(emptyList())
        every { insert.invoke(any(), any()) } returns mockk<Job>()
        every { update.invoke(any(), any()) } returns mockk<Job>()
        every { delete.invoke(any(), any()) } returns mockk<Job>()

        val vm = TodoListViewModel(getAll, insert, update, delete)

        vm.addTodo("t", "d")

        verify { insert.invoke(any(), match { it.title == "t" && it.description == "d" }) }
    }
}

