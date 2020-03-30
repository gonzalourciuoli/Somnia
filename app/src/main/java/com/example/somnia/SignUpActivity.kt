package com.example.somnia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        val signup_button = findViewById<Button>(R.id.sign_up) as Button
        signup_button.setOnClickListener {
            val intent = Intent(this@SignUpActivity, logInActivity::class.java)
            startActivity(intent)
        }

        val login_button = findViewById<Button>(R.id.log_in) as Button
        login_button.setOnClickListener {
            val intent = Intent(this@SignUpActivity, logInActivity::class.java)
            startActivity(intent)
        }
    }




}