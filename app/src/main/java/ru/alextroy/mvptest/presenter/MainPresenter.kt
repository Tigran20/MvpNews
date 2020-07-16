package ru.alextroy.mvptest.presenter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ru.alextroy.mvptest.contract.MainView
import ru.alextroy.mvptest.model.Article
import ru.alextroy.mvptest.pagination.ArticlesDataSourceFactory
import ru.alextroy.mvptest.utils.PAGE_SIZE

class MainPresenter(private var mainView: MainView?, dataSourceFactory: ArticlesDataSourceFactory) {

    var articles: LiveData<PagedList<Article>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(false)
            .build()
        articles = LivePagedListBuilder(dataSourceFactory, config).build()
    }

    fun getData() {
        mainView?.setData()
    }

    fun onDestroy() {
        mainView = null
    }
}