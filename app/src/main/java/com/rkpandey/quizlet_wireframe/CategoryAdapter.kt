package com.rkpandey.quizlet_wireframe

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rkpandey.quizlet_wireframe.fragments.HomeFragment

open class CategoryAdapter(val context: Context, val mList: List<Post>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(context)
            .inflate(R.layout.category_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val categoryItem = mList.get(position)
        holder.bind(categoryItem)

        holder.tvCat.setOnClickListener {
            val intent = (Intent(this.context, CardSetScreen::class.java))
            Log.i("categoryAdapter", "Text: "+ categoryItem.getCategory())
            intent.putExtra("category", categoryItem.getCategory())
            ContextCompat.startActivity(this.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvCat: TextView = itemView.findViewById(R.id.tvCat)

        fun bind(post: Post) {
            tvCat.text = post.getCategory()
            //tvWord.text = post.getWord().toString()
            //tvDefinition.text = post.getDefinition()
        }
    }

}