import com.takari.fetchrewardscodingtest.homePage.data.ItemInfo
import retrofit2.http.GET


/**
 * Retrofit interface for accessing the Fetch Rewards data from the provided API.
 */
interface FetchRewardsApi {

    companion object {
        const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com"
    }

    /**
     * Fetches a list of item information from the Fetch Rewards API endpoint.
     *
     * @return A list of [ItemInfo] objects representing the items fetched from the API.
     */
    @GET("/hiring.json")
    suspend fun getFetchRewardsItemInfo(): List<ItemInfo>

}
