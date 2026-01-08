package msh.todolist.ui.components.common

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LayoutTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

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

        // Verificar que el título del TopBar está presente
        composeTestRule.onNodeWithText(title).assertIsDisplayed()

        // Verificar que el contenido provisto se muestra
        composeTestRule.onNodeWithText("BODY_CONTENT").assertIsDisplayed()

        // Verificar que los items del BottomBar están presentes
        composeTestRule.onNodeWithText("Tareas").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ajustes").assertIsDisplayed()
    }
}

