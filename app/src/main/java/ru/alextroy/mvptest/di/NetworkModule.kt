package ru.alextroy.mvptest.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.alextroy.mvptest.api.NewsApi
import ru.alextroy.mvptest.data.AppDatabase
import ru.alextroy.mvptest.data.ArticleDao
import ru.alextroy.mvptest.utils.BASE_URL
import javax.inject.Singleton

@Module
object NetworkModule {
    @Singleton
    @Provides
    internal fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "dbName")
            .build()
    }

    @Singleton
    @Provides
    fun provideDao(database: AppDatabase): ArticleDao = database.articleDao()
}