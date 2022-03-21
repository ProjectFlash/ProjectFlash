package com.rkpandey.quizlet_wireframe

import Postadapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rkpandey.quizlet_wireframe.Post
import com.rkpandey.quizlet_wireframe.R

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        val recyclerview = findViewById<RecyclerView>(R.id.rvPosts)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Post>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..3) {
            data.add(Post())
        }

        // This will pass the ArrayList to our Adapter
        val adapter = Postadapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

}
}