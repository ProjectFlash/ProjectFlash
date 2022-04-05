package com.rkpandey.quizlet_wireframe.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.rkpandey.quizlet_wireframe.CategoryAdapter
import com.rkpandey.quizlet_wireframe.Post
import com.rkpandey.quizlet_wireframe.R

class CategoryFragment : Fragment() {

    lateinit var categoryRecyclerView: RecyclerView
    lateinit var adapter: CategoryAdapter
    // var allPosts: MutableList<Post> = mutableListOf()
    var categoryItems: MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up views and click listeners here
        categoryRecyclerView = view.findViewById(R.id.rvCat)

        // 1. create layout for each row in list (item_post.xml)
        // 2. create data source for each row (Post class)
        // 3. Create adapter that will bridge data and row layout
        // 4. set adapter on RecyclerView
        adapter = CategoryAdapter(requireContext(), categoryItems)
        categoryRecyclerView.adapter = adapter
        // 5. Set layout manager on RecyclerView
        categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Log.i(TAG, "Current User: "+ ParseUser.getCurrentUser().username)
        queryPosts()

    }

    fun queryPosts() {
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.findInBackground(object : FindCallback<Post> {
            override fun done(cards: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    // trouble
                    Log.e("TAG", "Error fetching posts")
                } else {
                    if (cards != null) {
                        categoryItems.addAll(cards)
                        removeDuplicates(categoryItems)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    open fun removeDuplicates(allPosts2: MutableList<Post>) {
        var tempPosts: MutableList<Post> = mutableListOf()
        var catNames = mutableSetOf<String>()

        for(post in allPosts2){
            catNames.add(post.getCategory().toString())
            Log.i(TAG, "catNames" + catNames)
        }

        var counter = 0
        for(cat in catNames){
            tempPosts.add(counter, Post())
            tempPosts[counter].setCategory(cat)
            counter++
        }
        allPosts2.clear()

        allPosts2.addAll(tempPosts)
    }

    companion object {
        const val TAG = "CategoryFragment"
    }
}