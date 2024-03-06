package com.takari.fetchrewardscodingtest.homePage.ui


import FetchRewardsApi
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.takari.fetchrewardscodingtest.homePage.data.HomePageRepository
import com.takari.fetchrewardscodingtest.homePage.data.ItemInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Responsible for preparing and managing data for the UI related to the Fetch Reward's API.
 *
 * This ViewModel exposes a [StateFlow] object for the UI to observe and react to state changes.
 */
class HomePageViewModel(private val repository: HomePageRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(emptyList<HomePageUIState>())
    val uiState: StateFlow<List<HomePageUIState>> = _uiState

    /**
     * Retrieves a list of [ItemInfo] objects and populates [uiState] by mapping them into
     * [HomePageUIState] objects.
     */
    fun fetchItemInfo() {
        viewModelScope.launch {
            try {
                val itemInfo: List<ItemInfo> = repository.getFetchRewardsItemInfo()

                val groupedAndSortedItemInfo = sortAndGroupItemInfo(itemInfo)

                _uiState.value = convertItemInfoToUiState(groupedAndSortedItemInfo)
            } catch (e: Exception) {
                Log.d("takari", "Fetch Rewards API call failed: $e")
            }
        }
    }

    /**
     * Returns a map containing entries grouped by "listId". All null and empty names will
     * be filtered out, and all items will be sorted according to their ids.
     *
     * @param [itemInfo] list that contains items retrieved from Fetch Reward's API.
     */
    fun sortAndGroupItemInfo(itemInfo: List<ItemInfo>): Map<Int, List<ItemInfo>> {
        return itemInfo
            .sortedBy { item -> item.listId }
            .groupBy { item -> item.listId }
            .mapValues { (_, items) -> items.filterNot { item -> item.name.isNullOrEmpty() } }
            .mapValues { (_, items) -> items.sortedBy { item -> item.id } }
    }

    /**
     * Flattens the sublist within [ItemInfo] into a list of [HomePageUIState] in order
     * to effectively display grouped [ItemInfo] on the UI without deeply nesting composables.
     *
     * @param [items] map that contains items retrieved from Fetch Reward's API
     * grouped by "listId".
     */
    fun convertItemInfoToUiState(items: Map<Int, List<ItemInfo>>): List<HomePageUIState> {
        val state = mutableListOf<HomePageUIState>()

        items.forEach { (key, value) ->
            state.add(HomePageUIState.Header(key))

            value.forEach { valueItem ->
                state.add(HomePageUIState.Items(valueItem))
            }
        }

        return state
    }

    companion object {
        // Initializes HomePageViewModel with it's dependencies.
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val fetchRewardsApi = Retrofit.Builder()
                    .baseUrl(FetchRewardsApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(FetchRewardsApi::class.java)

                HomePageViewModel(repository = HomePageRepository(fetchRewardsApi))
            }
        }
    }
}
