package msh.todolist.usecases

import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import msh.todolist.domain.model.Todo
import msh.todolist.domain.repository.TodoRepository
import msh.todolist.domain.usecases.todo.GetAllTodosUseCase
import org.junit.Test

class GetAllTodosUseCaseTest {

    @Test
    fun `invoke returns flow from repository`() = runTest {
        val repo = mockk<TodoRepository>()
        val sample = Todo(id = 1L, title = "T1", description = "D1")
        every { repo.getAll() } returns flowOf(listOf(sample))

        val useCase = GetAllTodosUseCase(repo)

        val result = useCase().toList() // collect into list
        assertEquals(1, result.size)
        assertEquals(listOf(sample), result[0])
    }
}
