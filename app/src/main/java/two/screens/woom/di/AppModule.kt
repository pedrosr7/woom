package two.screens.woom.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import two.screens.woom.data.datasource.NetworkDataSource
import two.screens.woom.data.repository.RandomRepositoryImp
import two.screens.woom.domain.repository.RandomRepository
import two.screens.woom.domain.usecase.GetRandomListUseCase
import two.screens.woom.presentation.viewmodel.RandomListViewModel

val appModule = module {
    single { GetRandomListUseCase(get()) }
    single <RandomRepository> { RandomRepositoryImp(get()) }

    viewModel { RandomListViewModel(get()) }
}