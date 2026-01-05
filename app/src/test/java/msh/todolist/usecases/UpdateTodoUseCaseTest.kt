package msh.todolist.usecases

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import msh.todolist.domain.model.Todo
import msh.todolist.domain.repository.TodoRepository
import msh.todolist.domain.usecases.todo.UpdateTodoUseCase
import org.junit.Test

class UpdateTodoUseCaseTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @Test
    fun `invoke calls repository update`() = runTest {
        val repo = mockk<TodoRepository>()
        val todo = Todo(id = 1L, title = "A")
        coEvery { repo.update(todo) } returns Unit

        val useCase = UpdateTodoUseCase(repo, dispatcher)

        val job = useCase.invoke(this, todo)
        assertNotNull(job)
    }
}

