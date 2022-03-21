package com.rkpandey.quizlet_wireframe

import Postadapter
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)

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
        val recyclerview = findViewById<RecyclerView>(R.id.rvPosts)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Post>()

        // This loop will create 20 Views containing
        for (i in 1..20) {
            data.add(Post())
        }
        // the image with the count of view

        // This will pass the ArrayList to our Adapter
        val adapter = Postadapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }
}