package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.somnia.R
//import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_valuations.*
import kotlinx.android.synthetic.main.login.view.*

class Valuations : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_valuations)

        val save = findViewById<Button>(R.id.saveButton) as Button
        save.setOnClickListener {
            val intent = Intent(this@Valuations, Home::class.java)
            startActivity(intent)
            val numStars = ratingBar.numStars
            val sport_box = checkBox_sport.isChecked
            val coffee_box = checkBox_coffee.isChecked
            val alcohol_box = checkBox_alcohol.isChecked
            val valuation_comment = text_comment.text

        }

        val cancel = findViewById<Button>(R.id.cancelButton) as Button
        cancel.setOnClickListener {
            val intent = Intent(this@Valuations, Home::class.java)
            startActivity(intent)
        }
    }
}
