package msh.todolist.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import msh.todolist.domain.model.Todo
import msh.todolist.ui.components.settings.SettingsScreen
import msh.todolist.ui.components.todolist.ListScreen
import msh.todolist.ui.components.todolist.UiTodo
import msh.todolist.ui.viewmodel.TodoListViewModel
import msh.todolist.ui.constants.Routes

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val todoViewModel: TodoListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavHost(todoViewModel)
            }
        }
    }
}

@Composable
fun AppNavHost(viewModel: TodoListViewModel) {
    val navController = rememberNavController()
    val todosState by viewModel.todos.collectAsState()
    val uiTodos = todosState.map { entity ->
        UiTodo(
            id = entity.id,
            title = entity.title,
            description = entity.description ?: "",
            completed = entity.completed
        )
    }

    NavHost(navController = navController, startDestination = Routes.LIST) {
        composable(Routes.LIST) {
            ListScreen(
                onSettings = { navController.navigate(Routes.SETTINGS) },
                todos = uiTodos,
                onAdd = { title, description, completed ->
                    val job = viewModel.addTodo(title, description, completed)
                    job.join()
                },
                onDelete = { id -> viewModel.deleteTodo(id) },
                onToggle = { id, completed ->
                    val entity = todosState.find { it.id == id } ?: return@ListScreen
                    val updated = Todo(
                        id = entity.id,
                        title = entity.title,
                        description = entity.description,
                        completed = completed
                    )
                    viewModel.updateTodo(updated)
                },
                onSave = { id, title, description ->
                    val entity = todosState.find { it.id == id } ?: return@ListScreen
                    val updated = Todo(
                        id = entity.id,
                        title = title,
                        description = description,
                        completed = entity.completed
                    )
                    viewModel.updateTodo(updated)
                }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                onTaskSelected = {
                    val popped = navController.popBackStack(Routes.LIST, false)
                    if (!popped) {
                        navController.navigate(Routes.LIST) { launchSingleTop = true }
                    }
                },
                onSettingsSelected = {
                    if (navController.currentDestination?.route != Routes.SETTINGS) {
                        navController.navigate(Routes.SETTINGS) { launchSingleTop = true }
                    }
                }
            )
        }
    }
}
