package msh.todolist.ui.components.common

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LayoutRobolectricTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun layout_displaysTopBarContentAndBottomBar() {
        val title = "Pantalla Principal"

        composeTestRule.setContent {
            Layout(
                title = title,
                onTaskSelected = {},
                onSettingsSelected = {}
            ) {
                androidx.compose.material3.Text("BODY_CONTENT")
            }
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText("BODY_CONTENT").assertIsDisplayed()
        composeTestRule.onNodeWithText("Tareas").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ajustes").assertIsDisplayed()
    }
}

