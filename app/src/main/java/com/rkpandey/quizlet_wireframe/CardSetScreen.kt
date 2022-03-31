package com.rkpandey.quizlet_wireframe

import PostAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.rkpandey.quizlet_wireframe.fragments.HomeFragment

class CardSetScreen : AppCompatActivity() {
    private lateinit var rvCardSet: RecyclerView
    private val posts = mutableListOf<Post>()
    lateinit var adapter: SetAdapter
    lateinit var setName: String
    var homePosts: MutableList<Post> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cardset_recyclerview)
        setName = intent.getStringExtra("set").toString()
        Log.i("Deez", "Set name: "+setName)


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
                            if(post.getSetName() == setName){ //if the name does not show up
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