package ru.alextroy.mvptest.data

import androidx.paging.PagedList
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import ru.alextroy.mvptest.model.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAll(): Single<List<Article>>

    @Insert
    fun insertAll(articles: List<Article>): Completable

}