package com.rkpandey.quizlet_wireframe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categories)

        val homebutton = findViewById<ImageButton>(R.id.homebutton)
        homebutton.setOnClickListener {
            val Intent = (Intent(this, Homepage::class.java))
            startActivity(Intent)
        }
        val catgoriesbutton = findViewById<ImageButton>(R.id.categoriesbutton)
        catgoriesbutton.setOnClickListener {
            val Intent = (Intent(this, Categoriespage::class.java))
            startActivity(Intent)
        }
        val accountbutton = findViewById<ImageButton>(R.id.accountbutton)
        accountbutton.setOnClickListener {
            val Intent = (Intent(this, Signin::class.java))
            startActivity(Intent)
        }

        queryPosts()

        // Test out Parse Connection
        val firstObject = ParseObject("FirstClass")
        firstObject.put("message", "Hey ! First message from android. Parse is now connected")
        firstObject.saveInBackground {
            if (it != null) {
                it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
            } else {
                Log.d("MainActivity", "Object saved.")
            }
        }
    }
// Test out making queries
       fun queryPosts(){
           val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
           query.findInBackground(object: FindCallback<Post> {
               override fun done(cards: MutableList<Post>?, e: ParseException?) {
                    if (e != null){
                       // trouble
                       Log.e("MainActivity", "Error fetching posts")
                   } else {
                       if (cards != null) {
                           for (card in cards){
                            Log.i("MainActivity", "Card: " + card.getWord())
                           }
                       }
                   }
               }
           })
   }
}