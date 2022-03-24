package com.rkpandey.quizlet_wireframe.fragments

import PostAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser
import com.rkpandey.quizlet_wireframe.CardSet
import com.rkpandey.quizlet_wireframe.Post
import com.rkpandey.quizlet_wireframe.ProfileAdapter
import com.rkpandey.quizlet_wireframe.R

class ProfileFragment: HomeFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvUserName: TextView
        val tvTotalSet: TextView
        val tvLikeCount: TextView

        tvUserName= view.findViewById(R.id.userName)
        tvTotalSet = view.findViewById(R.id.tvTotalSet)
        tvLikeCount = view.findViewById(R.id.tvLikeCount)

        tvUserName.text = ParseUser.getCurrentUser().username

        tvTotalSet.text = "Total Sets: "
        tvLikeCount.text = "Total Likes: "

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
    }


    // only include query of the signed in user
    override fun queryPosts(){
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
                    if (posts != null){
                        for (post in posts){
                            Log.i(TAG, "Post： " + post.getWord() + ", Username: " + post.getUser()?.username)
                        }
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