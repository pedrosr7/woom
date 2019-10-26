package two.screens.woom.data.datasource

import retrofit2.http.GET
import retrofit2.http.Query
import two.screens.woom.domain.model.Results

interface NetworkDataSource {

    @GET("/")
    suspend fun getRandomItemAsync(@Query("results") results: Int): Results

}