package com.example.appvelitjose.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appvelitjose.R
import com.example.appvelitjose.models.Post

class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var posts: List<Post> = listOf()

    fun submitList(postList: List<Post>) {
        posts = postList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvBody: TextView = itemView.findViewById(R.id.tvBody)

        fun bind(post: Post) {
            tvTitle.text = post.title
            tvBody.text = post.body
        }
    }
}