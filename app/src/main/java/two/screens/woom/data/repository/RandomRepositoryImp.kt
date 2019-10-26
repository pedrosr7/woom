package two.screens.woom.data.repository

import two.screens.woom.core.Either
import two.screens.woom.core.Failure
import two.screens.woom.data.datasource.NetworkDataSource
import two.screens.woom.domain.model.RandomItem
import two.screens.woom.domain.repository.RandomRepository

class RandomRepositoryImp(
    private val networkDataSource: NetworkDataSource
) : RandomRepository {

    override suspend fun getRandomList(page: Int): Either<Failure, List<RandomItem>> {

        return try {
            val response = networkDataSource.getRandomItemAsync(page)

            Either.Right(response.randomItems)

        } catch (e: Exception) {
            Either.Left(Failure.ServerError)
        }

    }

}