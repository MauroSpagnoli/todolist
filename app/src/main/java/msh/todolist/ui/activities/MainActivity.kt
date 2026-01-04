package msh.todolist.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import msh.todolist.ui.components.settings.SettingsScreen
import msh.todolist.ui.components.todolist.ListScreen
import msh.todolist.ui.viewmodel.TodoListViewModel

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
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ListScreen(
                onSettings = { navController.navigate("settings") },
                viewModel = viewModel
            )
        }
        composable("settings") {
            SettingsScreen(
                onTaskSelected = {
                    val popped = navController.popBackStack("list", false)
                    if (!popped) {
                        navController.navigate("list") { launchSingleTop = true }
                    }
                },
                onSettingsSelected = {
                    if (navController.currentDestination?.route != "settings") {
                        navController.navigate("settings") { launchSingleTop = true }
                    }
                }
            )
        }
    }
}
