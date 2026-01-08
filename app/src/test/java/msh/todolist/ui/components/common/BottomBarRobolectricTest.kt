package msh.todolist.ui.components.common

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BottomBarRobolectricTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bottomBar_taskAndSettingsCallbacksAreCalled() {
        val taskClicked = mutableStateOf(false)
        val settingsClicked = mutableStateOf(false)

        composeTestRule.setContent {
            BottomBar(
                modifier = androidx.compose.ui.Modifier,
                onTaskSelected = { taskClicked.value = true },
                onSettingsSelected = { settingsClicked.value = true }
            )

            if (taskClicked.value) {
                androidx.compose.material3.Text("TASK_CLICKED")
            }
            if (settingsClicked.value) {
                androidx.compose.material3.Text("SETTINGS_CLICKED")
            }
        }

        // Click en 'Tareas' y comprobar callback
        composeTestRule.onNodeWithText("Tareas").performClick()
        composeTestRule.onNodeWithText("TASK_CLICKED").assertIsDisplayed()

        // Click en 'Ajustes' y comprobar callback
        composeTestRule.onNodeWithText("Ajustes").performClick()
        composeTestRule.onNodeWithText("SETTINGS_CLICKED").assertIsDisplayed()
    }
}

