package com.rkpandey.quizlet_wireframe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseUser

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)

        findViewById<Button>(R.id.signin_signInBtn).setOnClickListener {
            val intent = Intent(this@AccountActivity, Signinpage::class.java)
            startActivity(intent)
            finish()
        }
        findViewById<Button>(R.id.createacct).setOnClickListener {
            val intent = Intent(this@AccountActivity, CreateAccount::class.java)
            startActivity(intent)
            finish()
        }
    }
}