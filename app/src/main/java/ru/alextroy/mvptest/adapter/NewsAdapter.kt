package ru.alextroy.mvptest.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import ru.alextroy.mvptest.R
import ru.alextroy.mvptest.model.Article
import ru.alextroy.mvptest.utils.EXTRA_MESSAGE
import ru.alextroy.mvptest.utils.setPublishedAt
import ru.alextroy.mvptest.view.WebViewActivity

class NewsAdapter(private val context: Context) :
    PagedListAdapter<Article, NewsAdapter.NewsViewHolder>(NewsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView: View = inflater.inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val element = getItem(position)

        Glide.with(context).load(element?.urlToImage).into(holder.image)
        holder.title.text = element?.title
        holder.description.text = element?.description
        element?.publishedAt?.let { setPublishedAt(holder.date, it) }

        holder.cardView.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, element?.url)
            }
            context.startActivity(intent)
        }
    }

    class NewsViewHolder(itemView: View) : ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.card_view)
        val image: ImageView = itemView.findViewById(R.id.news_image_detail)
        val title: TextView = itemView.findViewById(R.id.news_title)
        val description: TextView = itemView.findViewById(R.id.news_description)
        val date: TextView = itemView.findViewById(R.id.news_published_date)
    }

    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}