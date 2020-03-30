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

        val save = findViewById<Button>(R.id.saveButton) as Button
        save.setOnClickListener {
            val intent = Intent(this@Valuations, Home::class.java)
            startActivity(intent)
        }

        val cancel = findViewById<Button>(R.id.cancelButton) as Button
        cancel.setOnClickListener {
            val intent = Intent(this@Valuations, Home::class.java)
            startActivity(intent)
        }
    }
}
