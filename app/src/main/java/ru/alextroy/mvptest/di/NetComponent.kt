package ru.alextroy.mvptest.di


import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.alextroy.mvptest.pagination.ArticlesPagedDataSource
import ru.alextroy.mvptest.view.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): NetComponent
    }

    fun inject(dataSource: ArticlesPagedDataSource)
    fun inject(activity: MainActivity)
}