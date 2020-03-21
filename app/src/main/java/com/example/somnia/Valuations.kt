package com.example.somnia

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Valuations : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valuations)

        val home_button = findViewById<ImageView>(R.id.principal_settings) as ImageView
        home_button.setOnClickListener {
            home_button.setColorFilter(Color.RED)
            val intent = Intent(this@Valuations, Home::class.java)
            startActivity(intent)
        }

        val settings_button = findViewById<ImageView>(R.id.settings_settings) as ImageView
        settings_button.setOnClickListener {
            settings_button.setColorFilter(Color.RED)
            val intent = Intent(this@Valuations, Settings::class.java)
            startActivity(intent)
        }

        val calculadora_button = findViewById<ImageView>(R.id.calculadora_settings) as ImageView
        calculadora_button.setOnClickListener {
            calculadora_button.setColorFilter(Color.RED)
            val intent = Intent(this@Valuations, Calculator::class.java)
            startActivity(intent)
        }

        val save_button = findViewById<Button>(R.id.button3) as Button
        save_button.setOnClickListener {
            val intent = Intent(this@Valuations, Home::class.java)
            startActivity(intent)
        }

        val cancel_button = findViewById<Button>(R.id.button2) as Button
        cancel_button.setOnClickListener {
            val intent = Intent(this@Valuations, Home::class.java)
            startActivity(intent)
        }
    }
}
