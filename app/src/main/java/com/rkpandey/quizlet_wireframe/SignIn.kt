package com.rkpandey.quizlet_wireframe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.parse.ParseUser

class SignIn: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signinpage)

        //check userlogin and if it exists, go to main activity
        if(ParseUser.getCurrentUser() != null){
            goToMainActivity()
        }

        findViewById<Button>(R.id.SignInButton).setOnClickListener{
            val userName = findViewById<EditText>(R.id.EnterUsername).text.toString();
            val password = findViewById<EditText>(R.id.EnterPassword);
            val passwordError = findViewById<TextView>(R.id.signInError)
            loginUser(userName, password, passwordError)
        }

    }

    private fun loginUser(userName: String, password: EditText, passwordError: TextView){
        ParseUser.logInInBackground(userName, password.text.toString(), ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Successful login")
                goToMainActivity()
            } else {
                Log.e(TAG, "Failed")
                passwordError.isVisible = true
                e.printStackTrace()
                Toast.makeText(this,"Error logging in", Toast.LENGTH_SHORT).show()
            }})
        )
    }


    private fun goToMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() //once successful login, closes login activity
    }

    companion object{
        val TAG = "LoginActivity"
    }
}