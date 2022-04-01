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

class MakeSetFragment : Fragment() {

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
        submitButton = view.findViewById<Button>(R.id.btnSubmit)
        addWordButton = view.findViewById<Button>(R.id.btnAddWord)
        submitButton.setOnClickListener{
            Toast.makeText(requireContext(), "Clicked the submit button", Toast.LENGTH_SHORT).show()
        }

        addWordButton.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked the add button", Toast.LENGTH_SHORT).show()

        }

    }





}