package two.screens.woom.domain.repository

import two.screens.woom.core.Either
import two.screens.woom.core.Failure
import two.screens.woom.domain.model.RandomItem

interface RandomRepository {

    /**
     * Function that sign up a user
     * @param page the current page
     * @param onResult Callback that returns the result object that contains [Failure] and [List<RandomItem>]
     *
     */
     suspend fun getRandomList(page: Int): Either<Failure, List<RandomItem>>
}