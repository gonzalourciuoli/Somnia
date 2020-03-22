package com.example.somnia

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.alarms.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        val register_button = findViewById<Button>(R.id.register) as Button
        register_button.setOnClickListener {
            val intent = Intent(this@RegisterActivity, logInActivity::class.java)
            startActivity(intent)
        }

        val cancel_button = findViewById<Button>(R.id.cancel) as Button
        cancel_button.setOnClickListener {
            val intent = Intent(this@RegisterActivity, logInActivity::class.java)
            startActivity(intent)
        }
    }




}