package com.example.newtestapplication.data.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newtestapplication.data.Article
import com.example.newtestapplication.data.NewsRepo
import org.koin.core.KoinComponent
import org.koin.core.inject

class NewsViewModel : BaseViewModel(), KoinComponent {

    private val newsRepo by inject<NewsRepo>()
    private val newLiveData by lazy { MutableLiveData<List<Article>>() }

         fun getTopNews() {
        postData(newLiveData) {
            newsRepo.getTopNews("us")
        }
    }

    fun getNewsLiveData() = ((newLiveData as LiveData<List<Article>>))
}