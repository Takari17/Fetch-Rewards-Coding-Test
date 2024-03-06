package com.takari.fetchrewardscodingtest.homePage.ui

import com.takari.fetchrewardscodingtest.homePage.data.ItemInfo

/**
 * Contains all attributes needed to populate the home page screen.
 */
sealed class HomePageUIState {
    data class Header(val listId: Int) : HomePageUIState()
    data class Items(val itemInfo: ItemInfo) : HomePageUIState()
}