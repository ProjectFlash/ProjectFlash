package com.rkpandey.quizlet_wireframe.fragments

import PostAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser
import com.rkpandey.quizlet_wireframe.Post
import com.rkpandey.quizlet_wireframe.R
/*
import com.rkpandey.quizlet_wireframe.adapter
import com.rkpandey.quizlet_wireframe.allPosts*/

lateinit var adapter: PostAdapter
lateinit var recyclerview: RecyclerView
var allPosts: MutableList<Post> = mutableListOf()


class ProfileFragment: Fragment(){
    var totalPosts: Int = 0
    lateinit var tvUserName: TextView
    lateinit var tvTotalSet: TextView
    lateinit var tvLikeCount: TextView
    lateinit var postsRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvUserName= view.findViewById(R.id.userName)
        tvTotalSet = view.findViewById(R.id.tvTotalSet)
        tvLikeCount = view.findViewById(R.id.tvLikeCount)

        tvUserName.text = ParseUser.getCurrentUser().username


        // Set up views and click listeners here
        postsRecyclerView = view.findViewById(R.id.rvPosts)

        // 1. create layout for each row in list (item_post.xml)
        // 2. create data source for each row (Post class)
        // 3. Create adapter that will bridge data and row layout
        // 4. set adapter on RecyclerView
        adapter = PostAdapter(requireContext(), allPosts)
        postsRecyclerView.adapter = adapter
        // 5. Set layout manager on RecyclerView
        postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        queryPosts()

        Log.i("Total2", "The total is "+totalPosts)
        //tvTotalSet.text = "Total Sets: "
        tvLikeCount.text = "Total Likes: "+ 5

    }

    fun removeDuplicates(allPosts2: MutableList<Post>, num: Int) {
        //super.removeDuplicates()
        var counter = 0
        var setNames = mutableSetOf<String>()
        var tempPosts: MutableList<Post> = mutableListOf()
        var currSet = allPosts2[0].getSetName().toString()
        for(post in allPosts2){
            setNames.add(post.getSetName().toString())
        }
        Log.i("Six", "Size: "+ setNames.size)

        counter = 0
        for(name in setNames){
            tempPosts.add(counter, Post())
            tempPosts[counter].setSetName(name)
            tempPosts[counter].setAuthor(ParseUser.getCurrentUser().username)
            counter++
        }

        for(post in tempPosts){
            Log.i("PostTemp", "Set name: "+post.getSetName())
        }
        allPosts2.clear()
        allPosts2.addAll(tempPosts)

    }

    // only include query of the signed in user
    fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        // Find all Post objects
        query.include(Post.KEY_USER)

        // only reutrn posts from currenlty sigend in user
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser())

        // return posts in descneding order
        query.addDescendingOrder("createdAt")

        query.setLimit(20); // limit to at most 20 results

        query.findInBackground(object: FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    e.printStackTrace()
                }else{
                    var currentSet = posts?.get(0)?.getSetName().toString()
                    if (posts != null){
                        for (post in posts){
                            if(currentSet != post.getSetName()){
                                currentSet = post.getSetName().toString()
                                totalPosts+=1
                            }
                            Log.i(TAG, "Post： " + post.getWord() + ", Username: " + post.getUser()?.username)
                        }
                        removeDuplicates(posts, 1)
                        Log.i("Total", "The total is "+posts.size)
                        tvTotalSet.text = "Total Sets: "+ posts.size
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
    companion object{
        val TAG = "ProfileFragment"
    }

}