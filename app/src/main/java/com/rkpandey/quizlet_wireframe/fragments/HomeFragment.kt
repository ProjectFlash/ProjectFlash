package com.rkpandey.quizlet_wireframe.fragments

import PostAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseQuery
import com.rkpandey.quizlet_wireframe.Post
import com.rkpandey.quizlet_wireframe.R
import com.parse.ParseException
import com.parse.ParseUser

open class HomeFragment : Fragment() {

    lateinit var postsRecyclerView: RecyclerView
    lateinit var adapter: PostAdapter
    var allPosts: MutableList<Post> = mutableListOf()
    var homePosts: MutableList<Post> = mutableListOf()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.homepage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up views and click listeners here
        postsRecyclerView = view.findViewById(R.id.rvPosts)

        // 1. create layout for each row in list (item_post.xml)
        // 2. create data source for each row (Post class)
        // 3. Create adapter that will bridge data and row layout
        // 4. set adapter on RecyclerView
        adapter = PostAdapter(requireContext(), homePosts)
        postsRecyclerView.adapter = adapter
        // 5. Set layout manager on RecyclerView
        postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

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
                            if(homePosts.size == 0){ //add first post element to the home feed
                                homePosts.add(counter, posts[0])
                            }else if(post.getUser()?.username != homePosts[counter].getUser()?.username){ //if the name does not show up
                                Log.i(TAG,"Comparison: "+ post.getUser()?.username+" != "+ homePosts[counter].getUser()?.username)
                                counter++
                                homePosts.add(counter, post)
                                Log.i(TAG,"User: "+ post.getUser()?.username)
                            }
                            Log.i(TAG, "Word： " + post.getWord() + ", Username: " + post.getUser()?.username)
                        }
                        allPosts.addAll(posts)
                        Log.i(TAG, "Posts: "+ allPosts)
                        removeDuplicates(homePosts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    open fun removeDuplicates(allPosts2: MutableList<Post>) {
        var tempPosts: MutableList<Post> = mutableListOf()
        var setNames = mutableSetOf<String>()
        var setAuthor: MutableList<String>
        setAuthor = mutableListOf()
        for(i in 0 until allPosts2!!.size-1){
            Log.i(TAG, "Dup array: "+i)
            if(allPosts2[i].getSetName() != allPosts2[i+1].getSetName()){
                //tempPosts.add(i, allPosts2[i])
                setNames.add(allPosts2[i].getSetName().toString())
                setAuthor.add(allPosts2[i].getUser()?.username.toString())
            }
        }
        var counter = 0
        for(name in setNames){
            tempPosts.add(counter, Post())
            tempPosts[counter].setSetName(name)
            counter++
        }

        counter = 0
        while(counter <= setNames.size-1){
            tempPosts[counter].setAuthor(setAuthor[counter])
            counter++
        }
        /*counter = 0
        for(author in setAuthor){
            tempPosts[counter].setAuthor(author)
            counter++
        }*/
        allPosts2.clear()
        allPosts2.addAll(tempPosts)
        Log.i(TAG, "Dup array: "+setNames)
    }

    companion object{
        const val TAG = "FeedFragment"
    }

    open fun removeDuplicates() {}
}