package com.example.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PostAdapter(val posts:ArrayList<String>): RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val data:TextView=itemView.findViewById(R.id.idtvcatogery)

    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): PostAdapter.ViewHolder {
        val view:View= LayoutInflater.from(parent.context).inflate(R.layout.catogery_horizontal_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder , position: Int)  {
        holder.data.text=posts[position]

    }

    override fun getItemCount(): Int {
        return posts.size
    }
}