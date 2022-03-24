package com.rkpandey.quizlet_wireframe

import PostAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.parse.Parse
import com.parse.ParseUser


open class ProfileAdapter(context: Context, mList: List<Post>) : PostAdapter(context, mList) {
/*
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(context)
            .inflate(R.layout.fragment_profile, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {

        val post = mList.get(position)
        holder.bind(post)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvUsername: TextView
        // val tvSetName: TextView
        //val tvDefinition: TextView

        init {
            tvUsername = itemView.findViewById(R.id.userName)

            //   tvSetName = itemView.findViewById(R.id.set_name)
        }
        //tvDefinition = itemView.findViewById(R.id.tvDefinition)

        fun bind(post: Post) {
            tvUsername.text = ParseUser.getCurrentUser().username
            // tvSetName.text = post.getSetName()
            //tvDefinition.text = post.getDefinition()
        }


    }*/
}