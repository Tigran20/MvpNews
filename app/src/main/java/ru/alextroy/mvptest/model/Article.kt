package ru.alextroy.mvptest.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.alextroy.mvptest.utils.Converters
import java.util.*

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val author: String?,
    val content: String?,
    val description: String?,
    @TypeConverters(Converters::class)
    val publishedAt: Date?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)