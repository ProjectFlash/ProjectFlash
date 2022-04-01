package com.rkpandey.quizlet_wireframe.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.parse.ParseUser
import com.rkpandey.quizlet_wireframe.R
import java.lang.Exception
import android.widget.Spinner
import com.parse.ParseFile
import com.rkpandey.quizlet_wireframe.Post


class MakeSetFragment : Fragment() {

    var frontCard: MutableList<String> = mutableListOf()
    var backCard: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_make_set, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            val spinner: Spinner = view.findViewById(R.id.spinner2)
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.cat_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }catch (e: Exception){
            Log.e("Create", "Error: "+e)
        }

        val submitButton: Button
        val addWordButton: Button
        val frontCardField: EditText
        val backCardField: EditText

        submitButton = view.findViewById<Button>(R.id.btnSubmit)
        addWordButton = view.findViewById<Button>(R.id.btnAddWord)
        submitButton.setOnClickListener{
            Toast.makeText(requireContext(), "Clicked the submit button", Toast.LENGTH_SHORT).show()
            Log.i("Words", "List of front: "+ frontCard)
            Log.i("Words", "List of back: "+ backCard)
            val setName = view.findViewById<EditText>(R.id.tfSetName).text.toString()
            val category = view.findViewById<Spinner>(R.id.spinner2).selectedItem.toString()
            Log.i("Words", "Set name: "+setName+" Category: "+category)
            addSetToDataBase(setName, category)
            view.findViewById<EditText>(R.id.tfFront).setText("")
            view.findViewById<EditText>(R.id.tfBack).setText("")
            frontCard.clear()
            backCard.clear()

        }

        addWordButton.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked the add button", Toast.LENGTH_SHORT).show()
            val front = view.findViewById<EditText>(R.id.tfFront).text.toString()
            val back = view.findViewById<EditText>(R.id.tfBack).text.toString()
            addWordToSet(front, back)
            view.findViewById<EditText>(R.id.tfFront).setText("")
            view.findViewById<EditText>(R.id.tfBack).setText("")
        }

    }

    private fun addSetToDataBase(name: String, category: String) {
        var allPosts: MutableList<Post> = mutableListOf()
        val currentUser = ParseUser.getCurrentUser()
        for((counter, card) in frontCard.withIndex()){
            allPosts.add(counter, Post())
            allPosts.elementAt(counter).setAuthor(currentUser?.username.toString())
            allPosts.elementAt(counter).setWord(card)
            allPosts.elementAt(counter).setDefinition(backCard.elementAt(counter))
            allPosts.elementAt(counter).setCategory(category)
            allPosts.elementAt(counter).setSetName(name)
            allPosts.elementAt(counter).setUser(currentUser)
            //allPosts.elementAt(counter).set
        }
        var postTest: Post
        for((counter, post) in allPosts.withIndex()){
            postTest = post
            postTest.saveInBackground{ exception ->
                if (exception != null){
                    Log.e("PostSet", "Error posting the post")
                    exception.printStackTrace()
                    Toast.makeText(requireContext(),"Something went wrong", Toast.LENGTH_SHORT).show()
                }else{
                    Log.i("PostSet", "Successful post creation")
                    Toast.makeText(requireContext(),"Post successfully made", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun addWordToSet(front: String, back: String) {
        frontCard.add(front)
        backCard.add(back)
    }


}