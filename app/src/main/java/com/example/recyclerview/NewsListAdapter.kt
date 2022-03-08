package com.example.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.*

class NewsListAdapter(private val listener:NewsItemClicked) : Adapter<NewsViewHolder>() {
    private val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): NewsViewHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.items_news,parent,false)
        val viewHolder=NewsViewHolder(view)
        view.setOnClickListener {
        listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder , position: Int) {
       val currentItem=items[position]
        holder.title_view.text=currentItem.title
    }

    override fun getItemCount(): Int {
        return items.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedNews: ArrayList<News>)
    {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: View) : ViewHolder(itemView)
{
    val title_view = itemView.findViewById<TextView>(R.id.textView)!!

}
interface NewsItemClicked{
    fun onItemClicked(item:News)
}