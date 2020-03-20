package com.example.app

import android.content.Intent
import android.graphics.Color
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.somnia.R
import kotlinx.android.synthetic.main.settings.*

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val chEmail_button = findViewById<TextView>(R.id.chEmail) as TextView
        chEmail_button.setOnClickListener {
            val intent = Intent(this@Settings, ChangeEmail::class.java)
            startActivity(intent)
        }

        val chPass_button = findViewById<TextView>(R.id.chPass) as TextView
        chPass_button.setOnClickListener {

        }

        val logout_button = findViewById<TextView>(R.id.logout) as TextView
        logout_button.setOnClickListener {

        }

        val delAcc_button = findViewById<TextView>(R.id.delAcc) as TextView
        delAcc_button.setOnClickListener {

        }

        val home_button = findViewById<ImageView>(R.id.principal_settings) as ImageView
        home_button.setOnClickListener {
            principal_settings.setColorFilter(Color.RED)
            val intent = Intent(this@Settings, Home::class.java)
            startActivity(intent)
        }
    }

}