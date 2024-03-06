package com.takari.fetchrewardscodingtest.homePage.data

/**
 * Represents an item retrieved from the Fetch Rewards API.
 */
data class ItemInfo(
    val id: Int,
    val listId: Int,
    val name: String?
)