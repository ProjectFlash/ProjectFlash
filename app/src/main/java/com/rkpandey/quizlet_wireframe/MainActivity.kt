package com.rkpandey.quizlet_wireframe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import com.rkpandey.quizlet_wireframe.fragments.CategoryFragment
import com.rkpandey.quizlet_wireframe.fragments.HomeFragment
import com.rkpandey.quizlet_wireframe.fragments.MakeSetFragment
import com.rkpandey.quizlet_wireframe.fragments.ProfileFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager

        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener{
                item ->
            lateinit var fragmentToShow: Fragment
            when (item.itemId){

                R.id.action_home -> {
                    // Go to Feed Fragment
                    fragmentToShow = HomeFragment()
                }
                R.id.action_category -> {
                    // Show Compose Fragment
                    fragmentToShow = CategoryFragment()
                }
                R.id.action_profile -> {
                    // Go to Profile
                    fragmentToShow = ProfileFragment()
                }
                R.id.action_create->{
                    fragmentToShow = MakeSetFragment()
                }
            }
            if (fragmentToShow != null){
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }
            true // we handle the user interaction
        }

        // Set default selection
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home
    }

    companion object{
        const val TAG = "MainActivity"
    }

   // queryPosts()

}

        // Test out Parse Connection
 /*      val firstObject = ParseObject("FirstClass")
        firstObject.put("message", "Hey ! First message from android. Parse is now connected")
        firstObject.saveInBackground {
            if (it != null) {
                it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
            } else {
                Log.d("MainActivity", "Object saved.")
            }
        }

   *//*     this.findViewById<Button>(R.id.signinbutton).setOnClickListener{
            val intent = Intent(this@MainActivity, Signinpage::class.java)
            startActivity(intent)
            finish()
        }*//*
    }

        // Test out making queries
        fun queryPosts(){
            val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
            query.findInBackground(object: FindCallback<Post>{
                override fun done(cards: MutableList<Post>?, e: ParseException?) {
                     if (e != null){
                        // trouble
                        Log.e("MainActivity", "Error fetching posts")
                    } else {
                        if (cards != null) {
                            for (card in cards){
                             Log.i("MainActivity", "User: " + card.getUser())
                            }
                        }
                    }
                }
            })
    }*/