package com.rkpandey.quizlet_wireframe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Signin  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)

        val homebutton = findViewById<ImageButton>(R.id.homebutton)
        homebutton.setOnClickListener {
            val Intent = (Intent(this, Homepage::class.java))
            startActivity(Intent)
        }
        val categoriesbutton = findViewById<ImageButton>(R.id.categoriesbutton)
        categoriesbutton.setOnClickListener {
            val Intent = (Intent(this, Categoriespage::class.java))
            startActivity(Intent)
        }
        val accountbutton = findViewById<ImageButton>(R.id.accountbutton)
        accountbutton.setOnClickListener {
            val Intent = (Intent(this, Signin::class.java))
            startActivity(Intent)
        }
        val signinbutton=findViewById<Button>(R.id.signinbutton)
        signinbutton.setOnClickListener {
            val Intent = (Intent(this, Signinpage::class.java))
            startActivity(Intent)
        }
    }
}