package com.example.fabhotels.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fabhotels.data.model.Article
import com.example.fabhotels.data.model.NewsArticle
import com.example.fabhotels.repository.Repository
import kotlinx.coroutines.launch

class NewsDashboardViewModel : ViewModel() {
    var mArticles: LiveData<NewsArticle?>? = null
    private var mutableSelectedArticle = MutableLiveData<Article>()
    var selectedItem: LiveData<Article> = mutableSelectedArticle

    fun setSelectArticle(article: Article) {
        mutableSelectedArticle.value = article
    }

    fun getSelectedArticle(): LiveData<Article> {
        selectedItem = mutableSelectedArticle
        return selectedItem
    }

    init {
        viewModelScope.launch {
            mArticles = Repository().getNewsArticle()
        }
    }


}