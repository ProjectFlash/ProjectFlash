package com.rkpandey.quizlet_wireframe

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

class CategorySetScreen : AppCompatActivity() {

    private lateinit var rvCardSet: RecyclerView
    private val posts = mutableListOf<Post>()
    lateinit var adapter: SetAdapter
    lateinit var catName: String
    var homePosts: MutableList<Post> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.homepage)
        //catName = intent.getStringExtra("set").toString()

        rvCardSet = findViewById(R.id.rvSetPosts)
        adapter = SetAdapter(this, homePosts)
        rvCardSet.adapter = adapter
        rvCardSet.layoutManager = LinearLayoutManager(this)

        queryPosts()
    }

    // Query for all post in the server
    open fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        // Find all Post objects
        query.include(Post.KEY_USER)

        // return posts in descneding order
        query.addDescendingOrder("createdAt")

        query.setLimit(20); // limit to at most 20 results

        query.findInBackground(object: FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                var counter: Int = 0; //counter to iterate through homePosts

                if (e != null) {
                    e.printStackTrace()
                }else{
                    if (posts != null){
                        for (post in posts){
                            if(post.getCategory() == catName){ //if the name does not show up
                                homePosts.add(counter, post)
                            }
                        }
                        //removeDuplicates(homePosts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }




}