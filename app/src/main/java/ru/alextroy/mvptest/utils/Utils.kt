package ru.alextroy.mvptest.utils

import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

const val BASE_URL = "https://newsapi.org/v2/"
const val Q = "android"
const val FROM = "2019-04-00"
const val SORT_BY = "publishedAt"
const val API_KEY = "26eddb253e7840f988aec61f2ece2907"
const val EXTRA_MESSAGE = "EXTRA_MESSAGE"
const val LANG = "ru"
const val PAGE_SIZE = 5
const val INITIAL_PAGE_KEY = 1

private val dateFormatter = SimpleDateFormat("HH:mm, dd MMM yyyy", Locale.getDefault())

fun setPublishedAt(view: TextView, date: Date) {
    view.text = dateFormatter.format(date)
}


