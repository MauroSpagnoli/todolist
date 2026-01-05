package msh.todolist.usecases

import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import msh.todolist.domain.repository.TodoRepository
import msh.todolist.domain.usecases.todo.DeleteTodoUseCase
import org.junit.Test

class DeleteTodoUseCaseTest {

    private val dispatcher = UnconfinedTestDispatcher()

    @Test
    fun `invoke calls repository delete`() = runTest {
        val repo = mockk<TodoRepository>()
        coEvery { repo.delete(1L) } returns Unit

        val useCase = DeleteTodoUseCase(repo, dispatcher)

        val job = useCase.invoke(this, 1L)
        assertNotNull(job)
    }
}

