package ru.alextroy.mvptest.model

data class NewsTop(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)