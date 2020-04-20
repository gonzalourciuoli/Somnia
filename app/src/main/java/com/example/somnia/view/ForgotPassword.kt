package com.example.somnia.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.R

class ForgotPassword: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val send = findViewById<Button>(R.id.send) as Button
        send.setOnClickListener {
                Toast.makeText(this, "The code has been send to your email" , Toast.LENGTH_SHORT ).show()
        }

        val skip = findViewById<Button>(R.id.skip) as Button
        skip.setOnClickListener {
            val intent = Intent(this@ForgotPassword, logInActivity::class.java)
            startActivity(intent)
        }
    }
}