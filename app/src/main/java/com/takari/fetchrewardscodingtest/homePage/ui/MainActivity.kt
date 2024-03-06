package com.takari.fetchrewardscodingtest.homePage.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

/**
 * MainActivity is the sole screen and entry point of the application.
 */
class MainActivity : ComponentActivity() {

    private val homePageViewModel: HomePageViewModel by viewModels { HomePageViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homePageViewModel.fetchItemInfo()

        setContent { HomePageScreen(homePageViewModel) }
    }
}
