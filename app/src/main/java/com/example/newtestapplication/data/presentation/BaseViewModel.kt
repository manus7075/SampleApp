package com.example.newtestapplication.data.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newtestapplication.data.core.Either
import com.example.theaterbookapp.data.core.MyException
import kotlinx.coroutines.*
import org.koin.core.KoinComponent

open class BaseViewModel : ViewModel(), KoinComponent {

    val progressLiveData by lazy { MutableLiveData<Boolean>() } // loader visibility
    val errorLiveData by lazy { MutableLiveData<Exception>() } // Error dialogs

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun <R> postData(observer: MutableLiveData<R>, result: suspend () -> Either<MyException, R>) {
        progressLiveData.postValue(false)
        viewModelScope.launch(Dispatchers.IO) {
            progressLiveData.postValue(true)
            result.invoke().either({
                progressLiveData.postValue(false)
                errorLiveData.postValue(it)
            }, {
                progressLiveData.postValue(false)
                observer.postValue(it)
            })
        }

    }


    override fun onCleared() {
        viewModelJob.cancel()
        uiScope.coroutineContext.cancelChildren()
        super.onCleared()
    }
}