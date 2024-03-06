package com.takari.fetchrewardscodingtest.homePage.data

import FetchRewardsApi

/**
 * Repository used for fetching data from the Fetch Rewards API. Abstracts any data source
 * complexities and acts as a single source of truth for all data related operations on
 * the home page.
 */
class HomePageRepository(private val fetchRewardsApi: FetchRewardsApi) {

    /**
     * Retrieves item info from the Fetch Rewards API and returns it as a List.
     *
     * @return List containing [ItemInfo] data from Fetch Rewards API.
     */
    suspend fun getFetchRewardsItemInfo(): List<ItemInfo> {
        return fetchRewardsApi.getFetchRewardsItemInfo()
    }
}