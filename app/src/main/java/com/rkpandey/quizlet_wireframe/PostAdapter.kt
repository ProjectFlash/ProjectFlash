package com.rkpandey.quizlet_wireframe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter (val context : Context, val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        //specify the layout file
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_flashcard, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvSetName: TextView
        //val ivImage : ImageView
        val author : TextView

        init{
            tvSetName = itemView.findViewById(R.id.info_text)
            //ivImage = itemView.findViewById(R.id.photoPostView)
            author = itemView.findViewById(R.id.authorFlash)
        }

        //bind method
        fun bind(post: Post){
            tvSetName.text = post.getSetName()
            author.text = post.getUser()?.username
            //Glide.with(itemView.context).load(post.getImage()?.url).into(ivImage)
        }


    }

}