package two.screens.woom

import com.google.gson.JsonArray
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import retrofit2.Response
import two.screens.woom.core.Either
import two.screens.woom.core.Failure
import two.screens.woom.data.datasource.NetworkDataSource
import two.screens.woom.data.repository.RandomRepositoryImp
import two.screens.woom.domain.model.RandomItem
import two.screens.woom.domain.model.Results

@RunWith(JUnit4::class)
class RandomRepositoryTest {

    private lateinit var repository: RandomRepositoryImp

    private lateinit var networkDataSource: NetworkDataSource

    private lateinit var results: Results

    private val randomItems = listOf<RandomItem>()

    @Before
    fun setUp() {
        networkDataSource = mock()
        results = mock()
        val mockException: HttpException = mock()

        runBlocking {
            whenever(networkDataSource.getRandomItemAsync(eq(100))).thenThrow(mockException)
            whenever(networkDataSource.getRandomItemAsync(eq(10))).thenReturn(results)
        }
        repository = RandomRepositoryImp(networkDataSource)
    }

    @Test
    fun `getRandomList when valid page is requested, then random list is returned`() =
        runBlocking {
            assertEquals(Either.Right(randomItems), repository.getRandomList(10))
        }


    @Test
    fun `getRandomList when valid page is requested, but some server error occurs then error is returned`() =
        runBlocking {
            assertEquals(Either.Left(Failure.ServerError), repository.getRandomList(100))
        }


}