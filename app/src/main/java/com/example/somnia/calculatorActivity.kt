package com.example.somnia

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class calculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val home_button = findViewById<ImageView>(R.id.principal_settings) as ImageView
        home_button.setOnClickListener {
            home_button.setColorFilter(Color.RED)
            val intent = Intent(this@calculatorActivity, Home::class.java)
            startActivity(intent)
        }

        val settings_button = findViewById<ImageView>(R.id.settings_settings) as ImageView
        settings_button.setOnClickListener {
            settings_button.setColorFilter(Color.RED)
            val intent = Intent(this@calculatorActivity, Settings::class.java)
            startActivity(intent)
        }

    }
}
