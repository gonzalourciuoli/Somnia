package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.somnia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Buttons_ListCalendar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons__list_calendar)
        val calendar = findViewById<Button>(R.id.calendar) as Button
        calendar.setOnClickListener {
            val intent = Intent(this, Calendar::class.java)
            startActivity(intent)
        }

        val list = findViewById<Button>(R.id.button_listValuations) as Button
        list.setOnClickListener {
            val intent = Intent(this, InfoValuations::class.java)
            startActivity(intent)

        }

        val returnButton = findViewById<Button>(R.id.return_button) as Button
        returnButton.setOnClickListener {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

}
