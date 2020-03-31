package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.somnia.R

class logInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val login_button = findViewById<Button>(R.id.login) as Button
        login_button.setOnClickListener {
            val intent = Intent(this@logInActivity, Home::class.java)
            startActivity(intent)
        }

        val signup_button = findViewById<Button>(R.id.sign_up) as Button
        signup_button.setOnClickListener {
            val intent = Intent(this@logInActivity, SignUpActivity::class.java)
            startActivity(intent)

        }


    }

}