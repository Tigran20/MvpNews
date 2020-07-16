package ru.alextroy.mvptest.pagination

import androidx.paging.DataSource
import ru.alextroy.mvptest.model.Article

class ArticlesDataSourceFactory : DataSource.Factory<Int, Article>() {
    override fun create() = ArticlesPagedDataSource()
}