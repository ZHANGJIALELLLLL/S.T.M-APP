package com.example.cp3406_stm_app

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.cp3406_stm_app.ui.LoginScreen
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {


    @get:Rule
//    val composeRule = createAndroidComposeRule<MainActivity>()
    val composeRule = createComposeRule()



    @Test
    fun loginButton_disabled_whenEmptyEmail() {
        composeRule.setContent {
            val navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            LoginScreen(navController = navController)
        }

//        composeRule.onNode(hasSetTextAction()).performTextInput("")
        composeRule.onNodeWithText("Continue").assertIsNotEnabled()
    }

    @Test
    fun loginButton_enabled_whenEmailEntered() {
        composeRule.setContent {
            val navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            LoginScreen(navController = navController)
        }

        composeRule.onNodeWithText("Email").performTextInput("user@example.com")
        composeRule.onNodeWithText("Continue").assertIsEnabled()
    }

}
