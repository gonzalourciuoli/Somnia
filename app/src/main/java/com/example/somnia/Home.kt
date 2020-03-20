package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.somnia.R
import kotlinx.android.synthetic.main.principal.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.principal)

        val settings_button = findViewById<ImageView>(R.id.settings_principal) as ImageView
        settings_button.setOnClickListener {
            val intent = Intent(this@Home, Settings::class.java)
            startActivity(intent)
        }
    }
}
