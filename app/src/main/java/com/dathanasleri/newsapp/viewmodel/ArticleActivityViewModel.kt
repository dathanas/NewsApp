package com.dathanasleri.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dathanasleri.newsapp.models.ArticlesList
import com.dathanasleri.newsapp.network.RetroInstance
import com.dathanasleri.newsapp.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ArticleActivityViewModel: ViewModel() {
    private var articlesListLiveData: MutableLiveData<ArticlesList> = MutableLiveData()

    fun getArticleListObserver(): MutableLiveData<ArticlesList>{
        return articlesListLiveData
    }

    fun makeAPICall() {
        //Make API call in IO and keep the response in articlesListLiveData
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromAPI(" ")
            articlesListLiveData.postValue(response)
        }
    }
}