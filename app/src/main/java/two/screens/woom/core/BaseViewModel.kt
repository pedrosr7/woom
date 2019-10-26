package two.screens.woom.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure> = MutableLiveData()

    fun handleFailure(failure: Failure) {
        this.failure.postValue(failure)
    }

}