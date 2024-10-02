package fr.eni.mod5demotestscompose

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class PasswordFieldShowHideTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun tester_show() {
        composeTestRule.setContent { PasswordField(Modifier) }

        composeTestRule
            .onNodeWithTag(TEST_TAG_TEXTFIELD_PWD)
            .performTextInput("Hello world")

        composeTestRule
            .onNodeWithContentDescription("Montrer mot de passe")
            .performClick()

        composeTestRule
            .onNodeWithTag(TEST_TAG_TEXTFIELD_PWD)
            .assert(hasText("Hello world"))

        composeTestRule
            .onNodeWithContentDescription("Cacher mot de passe")
            .assertIsDisplayed()
    }
}