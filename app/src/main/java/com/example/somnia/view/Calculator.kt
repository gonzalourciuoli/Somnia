package com.example.somnia.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.R

public class Calculator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val calendar = findViewById<Button>(R.id.calendar) as Button
        calendar.setOnClickListener {
            val intent = Intent(this@Calculator, Calendar::class.java)
            startActivity(intent)
        }
    }
}