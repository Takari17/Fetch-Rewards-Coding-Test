package com.takari.fetchrewardscodingtest.homePage.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.takari.fetchrewardscodingtest.homePage.data.ItemInfo
import com.takari.fetchrewardscodingtest.homePage.ui.theme.FetchRewardsCodingTestTheme

/**
 * Contains all composables displayed on the home page screen. The screen displays a list
 * of [HomePageUIState] through a LazyColumn containing headers and items.
 */

@Composable
fun HomePageScreen(viewModel: HomePageViewModel = viewModel(factory = HomePageViewModel.Factory)) {
    val uiState by viewModel.uiState.collectAsState()

    FetchRewardsCodingTestTheme {

        LazyColumn {
            items(uiState) { item ->
                when (item) {
                    is HomePageUIState.Header -> Text(
                        text = "List ID: ${item.listId}",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(8.dp)
                    )

                    is HomePageUIState.Items -> HomePageItem(item.itemInfo)
                }
            }
        }
    }
}


@Composable
fun HomePageItem(item: ItemInfo) {
    Card(
        Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "ID: ${item.id}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "Name: ${item.name}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        Text(
            text = "List ID: ${item.listId}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePageScreenPreview() {
    HomePageScreen()
}