package ru.alextroy.mvptest.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.news_list.*
import ru.alextroy.mvptest.R
import ru.alextroy.mvptest.adapter.NewsAdapter
import ru.alextroy.mvptest.contract.MainView
import ru.alextroy.mvptest.data.ArticleDao
import ru.alextroy.mvptest.di.App
import ru.alextroy.mvptest.pagination.ArticlesDataSourceFactory
import ru.alextroy.mvptest.presenter.MainPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var mainPresenter: MainPresenter
    private lateinit var adapter: NewsAdapter

    @Inject
    lateinit var database: ArticleDao

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_list)

        App.instance.getComponent().inject(this)
        mainPresenter = MainPresenter(this, ArticlesDataSourceFactory())
        mainPresenter.getData()
    }

    override fun showProgress() {
    }

    override fun hideProgress() {
    }

    override fun setData() {
        adapter = NewsAdapter(this)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        mainPresenter.articles.observe(this, Observer { adapter.submitList(it) })
    }

    override fun setDataError(strError: String) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()
    }
}