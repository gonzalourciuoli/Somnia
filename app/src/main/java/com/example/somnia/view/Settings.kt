package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.somnia.R

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val chEmail_button = findViewById<TextView>(R.id.ch_Email) as TextView
        chEmail_button.setOnClickListener {
            val intent = Intent(this@Settings, ChangeEmail::class.java)
            startActivity(intent)
        }

        val chPass_button = findViewById<TextView>(R.id.ch_Pass) as TextView
        chPass_button.setOnClickListener {
            val intent = Intent(this@Settings, ChangePassword::class.java)
            startActivity(intent)
        }

        val logout_button = findViewById<TextView>(R.id.log_out) as TextView
        logout_button.setOnClickListener {
            val intent = Intent(this@Settings, logInActivity::class.java)
            startActivity(intent)
        }

        val delAcc_button = findViewById<TextView>(R.id.del_Acc) as TextView
        delAcc_button.setOnClickListener {
            val intent = Intent(this@Settings, logInActivity::class.java)
            startActivity(intent)
        }

    }

}