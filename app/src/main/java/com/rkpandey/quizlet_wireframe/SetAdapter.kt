package com.rkpandey.quizlet_wireframe

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

open class SetAdapter(val context: Context, val mList: List<Post>) : RecyclerView.Adapter<SetAdapter.ViewHolder>() {
    // create new views
    var isClicked = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SetAdapter.ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(context)
            .inflate(R.layout.flashcard_front, parent, false)

        return ViewHolder(view)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvWord: TextView
        val tvDef: TextView
        val cvView: CardView

        init {
            tvWord = itemView.findViewById(R.id.tvWord)
            cvView = itemView.findViewById(R.id.cvFront)
            tvDef = itemView.findViewById(R.id.textDefinition)
        }
        fun bind(post: Post) {
            tvWord.text = post.getWord().toString()
            tvDef.text = post.getDefinition().toString()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = mList.get(position)
        holder.bind(post)

        holder.cvView.setOnClickListener{
            if(!isClicked){
                isClicked = true
                holder.tvDef.visibility = View.VISIBLE
                holder.tvWord.visibility = View.INVISIBLE
            }else{
                isClicked = false
                holder.tvWord.visibility = View.VISIBLE
                holder.tvDef.visibility = View.INVISIBLE
            }

        }


    }

}