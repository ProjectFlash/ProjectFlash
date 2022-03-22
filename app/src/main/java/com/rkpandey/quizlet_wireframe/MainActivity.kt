package com.rkpandey.quizlet_wireframe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)
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
        this.findViewById<Button>(R.id.signinbutton).setOnClickListener{
            val intent = Intent(this@MainActivity, Signinpage::class.java)
            startActivity(intent)
            finish()
        }
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
    }
}