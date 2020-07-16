package ru.alextroy.mvptest.pagination

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.alextroy.mvptest.api.NewsApi
import ru.alextroy.mvptest.data.AppDatabase
import ru.alextroy.mvptest.data.ArticleDao
import ru.alextroy.mvptest.di.App
import ru.alextroy.mvptest.model.Article
import ru.alextroy.mvptest.model.NewsTop
import ru.alextroy.mvptest.utils.*
import javax.inject.Inject


class ArticlesPagedDataSource : PageKeyedDataSource<Int, Article>() {

    private lateinit var disposable: Disposable
    var data = MutableLiveData<List<Article>>()

    @Inject
    lateinit var retrofitNetwork: NewsApi

    @Inject
    lateinit var database: ArticleDao

    init {
        App.instance.getComponent().inject(this)
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        loadArticles(INITIAL_PAGE_KEY, params.requestedLoadSize) {
            callback.onResult(it.articles, null, INITIAL_PAGE_KEY + 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        loadArticles(params.key, params.requestedLoadSize) {
            callback.onResult(it.articles, params.key + 1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        loadArticles(params.key, params.requestedLoadSize) {
            callback.onResult(it.articles, params.key - 1)
        }
    }

    private fun loadArticles(page: Int, pageSize: Int, callback: (NewsTop) -> Unit) {
        disposable = retrofitNetwork.getData(Q, FROM, SORT_BY, API_KEY, page, pageSize, LANG)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { result ->
                        callback(result)
                        database.insertAll(result.articles)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                {
                                    data.value = result.articles
                                    Log.e("DB", data.value?.size.toString())
                                },
                                {
                                }
                            )

                    },
                    { error ->
                        database.getAll()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                {
                                    data.postValue(it)
                                    Log.e("BLA", data.value?.size.toString())
                                },
                                {
                                }
                            )
                    }
                )
    }


}