package ru.alextroy.mvptest.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.alextroy.mvptest.model.NewsTop


interface NewsApi {
    @GET("everything/")
    fun getData(
        @Query("q") q: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("language") language: String
    ): Single<NewsTop>
}