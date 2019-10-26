package two.screens.woom.domain.usecase

import two.screens.woom.core.BaseParamsUseCase
import two.screens.woom.domain.model.RandomItem
import two.screens.woom.domain.repository.RandomRepository

class GetRandomListUseCase(
    private val repository: RandomRepository
): BaseParamsUseCase<List<RandomItem>, Int>() {
    override suspend fun run(params: Int) = repository.getRandomList(params)
}