package com.example.somnia

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.alarms.*

class logInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val login_button = findViewById<Button>(R.id.login) as Button
        login_button.setOnClickListener {
            val intent = Intent(this@logInActivity, Home::class.java)
            startActivity(intent)
        }

        val register_button = findViewById<Button>(R.id.register) as Button
        register_button.setOnClickListener {
            val intent = Intent(this@logInActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }




}