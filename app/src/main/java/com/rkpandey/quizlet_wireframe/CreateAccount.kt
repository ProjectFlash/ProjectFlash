package com.rkpandey.quizlet_wireframe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseUser

class CreateAccount: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createaccount)


        findViewById<Button>(R.id.createacctBtn).setOnClickListener {
            val username = findViewById<EditText>(R.id.EnterUsername).text.toString()
            val password = findViewById<EditText>(R.id.EnterPassword).text.toString()
            val password_confirmed = findViewById<EditText>(R.id.ReenterPassword).text.toString()
            val passwordError = findViewById<TextView>(R.id.passwordError)

            if (password == password_confirmed) {
                signUpUser(username, password)
            } else {
                passwordError.visibility = View.VISIBLE
            }
        }
    }

    private fun signUpUser(username: String, password: String) {
        // Create the ParseUser
        val user = ParseUser()
        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // Success
                val intent = Intent(this@CreateAccount, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // fail
                e.printStackTrace()
            }
        }

    }
}