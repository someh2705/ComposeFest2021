package com.example.compose.rally

import android.util.Log
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.toUpperCase
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.overview.OverviewBody
import org.junit.Rule
import org.junit.Test
import java.util.*

class TopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyTopAppBarTest_currentTabSelected() {
        val allScreen = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreen,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertIsSelected()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val allScreens = RallyScreen.values().toList()
        composeTestRule.setContent {
            RallyTopAppBar(
                allScreens = allScreens,
                onTabSelected = { },
                currentScreen = RallyScreen.Accounts
            )
        }

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase(Locale.getDefault())) and
                        hasParent(
                            hasContentDescription(RallyScreen.Accounts.name)
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun overviewScreen_alertsDisplayed() {
        composeTestRule.setContent {
            OverviewBody()
        }

        composeTestRule
            .onNodeWithText("Alerts")
            .assertIsDisplayed()
    }

    @Test
    fun optional_exercise() {
        composeTestRule.setContent {
            RallyApp()
        }

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Bills.name)
            .performClick()

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Bills.name)
            .assertIsSelected()

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .performClick()

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Accounts.name)
            .assertIsSelected()

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Overview.name)
            .performClick()

        composeTestRule
            .onNodeWithContentDescription(RallyScreen.Overview.name)
            .assertIsSelected()
    }
}
