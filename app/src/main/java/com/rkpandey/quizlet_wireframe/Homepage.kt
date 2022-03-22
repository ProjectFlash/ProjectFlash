package com.rkpandey.quizlet_wireframe

import PostAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

lateinit var adapter: PostAdapter
lateinit var recyclerview: RecyclerView
var allPosts: MutableList<Post> = mutableListOf()

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

        // Change to Fragments
        val homebutton = findViewById<ImageButton>(R.id.homebutton)
        homebutton.setOnClickListener {
            val Intent = (Intent(this, Homepage::class.java))
            startActivity(Intent)
        }
        val categoriesbutton = findViewById<ImageButton>(R.id.categoriesbutton)
        categoriesbutton.setOnClickListener {
            val Intent = (Intent(this, Categoriespage::class.java))
            startActivity(Intent)
        }
        val accountbutton = findViewById<ImageButton>(R.id.accountbutton)
        accountbutton.setOnClickListener {
            val Intent = (Intent(this, Signin::class.java))
            startActivity(Intent)
        }

        // getting the recyclerview by its id
        recyclerview = findViewById<RecyclerView>(R.id.rvPosts)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser())
        query.addDescendingOrder("createdAt")
        query.setLimit(20)

        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    e.printStackTrace()
                } else {
                    if (posts != null) {
                        allPosts.addAll(posts)
                        adapter = PostAdapter(allPosts)
                        recyclerview.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                    }
                    // ArrayList of class ItemsViewModel


                    // This loop will create 20 Views containing
                    //data.addEntry()

                    // the image with the count of view

                    // This will pass the ArrayList to our Adapter
                    //adapter = PostAdapter(data)

                    // Setting the Adapter with the recyclerview*/
                    //recyclerview.adapter = adapter }
                }

        })
    }}

