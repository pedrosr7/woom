package two.screens.woom.domain.repository

import two.screens.woom.core.Either
import two.screens.woom.core.Failure
import two.screens.woom.domain.model.RandomItem

interface RandomRepository {

    /**
     * Function that get the random list
     * @param page the current page
     *
     */
     suspend fun getRandomList(page: Int): Either<Failure, List<RandomItem>>
}