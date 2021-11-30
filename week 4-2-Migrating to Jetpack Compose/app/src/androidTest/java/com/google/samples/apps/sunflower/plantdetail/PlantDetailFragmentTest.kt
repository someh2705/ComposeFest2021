package com.google.samples.apps.sunflower.plantdetail

import android.os.Bundle
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.samples.apps.sunflower.GardenActivity
import com.google.samples.apps.sunflower.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlantDetailFragmentTest {

    @Rule
    @JvmField
    val composeTestRule = createAndroidComposeRule<GardenActivity>()


    @Before
    fun jumpToPlantDetailFragment() {
        composeTestRule.activityRule.scenario.onActivity { gardenActivity ->
            gardenActivity

            val bundle = Bundle().apply { putString("plantId", "malus-pumila") }
            findNavController(gardenActivity, R.id.nav_host).navigate(R.id.plant_detail_fragment)
        }
    }

    @Test
    fun testPlantName() {
        composeTestRule.onNodeWithText("Apple").assertIsDisplayed()
    }
}