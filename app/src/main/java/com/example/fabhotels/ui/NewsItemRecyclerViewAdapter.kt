package com.example.fabhotels.ui

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.fabhotels.R
import com.example.fabhotels.data.model.Article
import com.example.fabhotels.databinding.FragmentNewsListBinding
import com.example.fabhotels.utils.ArticleItemClickListener
import com.example.fabhotels.utils.Utils


class NewsItemRecyclerViewAdapter(
    private val context: Context,
    private var values: MutableList<Article> = mutableListOf(),
    private var clickListener: ArticleItemClickListener
) : RecyclerView.Adapter<NewsItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentNewsListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.headline.text = item.title
        holder.time.text = Utils.getTime(item.publishedAt)
        if (!TextUtils.isEmpty(item.urlToImage)) {
            Glide.with(context)
                .load(item.urlToImage)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.drawable.ic_broken_image)
                .placeholder(R.drawable.ic_image_placeholder)
                .into(holder.image)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setArticlesList(articles: MutableList<Article>?) {
        values.clear()
        if (articles != null) {
            values = articles
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentNewsListBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val headline: TextView = binding.articleHeadline
        val time: TextView = binding.uploadedTime
        val image: ImageView = binding.articleImage

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener.onClick(bindingAdapterPosition)
        }


    }


}