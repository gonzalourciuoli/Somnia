package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.somnia.R

class InfoValuations : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_valuations)

        val retrn = findViewById<Button>(R.id.retButton) as Button
        retrn.setOnClickListener {
            val intent = Intent(this@InfoValuations, Home::class.java)
            startActivity(intent)
        }
    }
}
