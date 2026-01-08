package msh.todolist.ui.components.common

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TopBarRobolectricTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun topBar_displaysTitleAndIcon() {
        val title = "Mi Título"

        composeTestRule.setContent {
            TopBar(title = title)
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Tick").assertIsDisplayed()
    }
}

