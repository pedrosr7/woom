package two.screens.woom.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import two.screens.woom.core.BaseViewModel
import two.screens.woom.domain.model.RandomItem
import two.screens.woom.domain.usecase.GetRandomListUseCase

class RandomListViewModel constructor(
    private val getRandomListUseCase: GetRandomListUseCase
) : BaseViewModel() {
    var showRandomList: MutableLiveData<List<RandomItem>> = MutableLiveData()
    var showProgressBar = MutableLiveData<Boolean>()

    var lastPage: Int = 0
    var randomItemsCache: MutableList<RandomItem> = mutableListOf()
    lateinit var randomItemSelected: RandomItem

    fun getRandomList(page: Int) {
        val realPage = if (lastPage > page) lastPage else page

        if(lastPage != page) showProgressBar.postValue(true)

        getRandomListUseCase.invoke(viewModelScope, realPage) { res ->
            showProgressBar.postValue(false)
            res.either(::handleFailure) {
                lastPage = realPage
                uniqueItems(it)
                showRandomList.postValue(randomItemsCache)
            }
        }
    }

    private fun uniqueItems(items: List<RandomItem>){
        for (i in items){
            if (!randomItemsCache.contains(i)) randomItemsCache.add(i)
        }
    }
}