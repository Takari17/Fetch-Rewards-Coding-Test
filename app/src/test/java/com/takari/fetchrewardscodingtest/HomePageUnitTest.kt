package com.takari.fetchrewardscodingtest

import FetchRewardsApi
import com.takari.fetchrewardscodingtest.homePage.data.HomePageRepository
import com.takari.fetchrewardscodingtest.homePage.data.ItemInfo
import com.takari.fetchrewardscodingtest.homePage.ui.HomePageUIState
import com.takari.fetchrewardscodingtest.homePage.ui.HomePageViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

class HomePageUnitTest {

    @Test
    fun `sortAndGroupItemInfo filters out empty or null names`() {
        val mockAPI = Mockito.mock(FetchRewardsApi::class.java)
        val repository = HomePageRepository(fetchRewardsApi = mockAPI)
        val viewModel = HomePageViewModel(repository = repository)

        val items = listOf(
            ItemInfo(id = 1, listId = 1, name = "Item 1"),
            ItemInfo(id = 2, listId = 1, name = ""), // This should be filtered out
            ItemInfo(id = 3, listId = 1, name = null), // This should be filtered out
        )

        val sortedAndGroupedItems = viewModel.sortAndGroupItemInfo(items)

        val expectedSize = 1

        print(sortedAndGroupedItems)

        assertEquals(expectedSize, sortedAndGroupedItems.size)
    }

    @Test
    fun `sortAndGroupItemInfo should sort by listId`() {
        val mockAPI = Mockito.mock(FetchRewardsApi::class.java)
        val repository = HomePageRepository(fetchRewardsApi = mockAPI)
        val viewModel = HomePageViewModel(repository = repository)

        val items = listOf(
            ItemInfo(id = 1, listId = 1, name = "Item 1"),
            ItemInfo(id = 2, listId = 1, name = "Item 2"),
            ItemInfo(id = 3, listId = 2, name = "Item 3"),
            ItemInfo(id = 4, listId = 2, name = "Item 4"),
        )

        val sortedAndGroupedItems = viewModel.sortAndGroupItemInfo(items)

        val expectedGroupedMap = mapOf(
            1 to listOf(
                ItemInfo(id = 1, listId = 1, name = "Item 1"),
                ItemInfo(id = 2, listId = 1, name = "Item 2"),
            ),
            2 to listOf(
                ItemInfo(id = 3, listId = 2, name = "Item 3"),
                ItemInfo(id = 4, listId = 2, name = "Item 4"),
            )
        )

        assertEquals(expectedGroupedMap, sortedAndGroupedItems)
    }

    @Test
    fun `convertItemInfoToUiState should convert a Map of listIds and ItemInfo into HomePageUIState`() {
        val mockAPI = Mockito.mock(FetchRewardsApi::class.java)
        val repository = HomePageRepository(fetchRewardsApi = mockAPI)
        val viewModel = HomePageViewModel(repository = repository)

        val items = mapOf(
            1 to listOf(
                ItemInfo(id = 1, listId = 1, name = "Item 1"),
                ItemInfo(id = 2, listId = 1, name = "Item 2"),
            ),
            2 to listOf(
                ItemInfo(id = 3, listId = 2, name = "Item 3"),
                ItemInfo(id = 4, listId = 2, name = "Item 4"),
            )
        )

        val actualState = viewModel.convertItemInfoToUiState(items)

        val expectedState = listOf(
            HomePageUIState.Header(1),
            HomePageUIState.Items(ItemInfo(id = 1, listId = 1, name = "Item 1")),
            HomePageUIState.Items(ItemInfo(id = 2, listId = 1, name = "Item 2")),

            HomePageUIState.Header(2),
            HomePageUIState.Items(ItemInfo(id = 3, listId = 2, name = "Item 3")),
            HomePageUIState.Items(ItemInfo(id = 4, listId = 2, name = "Item 4")),
        )

        assertEquals(expectedState, actualState)
    }
}