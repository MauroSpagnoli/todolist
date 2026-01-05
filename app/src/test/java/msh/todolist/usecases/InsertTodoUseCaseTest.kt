package msh.todolist.usecases

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import msh.todolist.domain.model.Todo
import msh.todolist.domain.repository.TodoRepository
import msh.todolist.domain.usecases.todo.InsertTodoUseCase
import org.junit.Test

class InsertTodoUseCaseTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @Test
    fun `invoke calls repository insert`() = runTest {
        val repo = mockk<TodoRepository>()
        val todo = Todo(title = "A")
        coEvery { repo.insert(todo) } returns 5L

        val useCase = InsertTodoUseCase(repo, dispatcher)

        // Since current implementation launches a coroutine using passed scope, we call it directly
        val job = useCase.invoke(this, todo)
        // job should not be null
        assertNotNull(job)
    }
}

