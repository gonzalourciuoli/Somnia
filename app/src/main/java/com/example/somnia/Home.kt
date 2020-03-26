package com.example.somnia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TimePicker

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val rate_last_sleep_button = findViewById<Button>(R.id.rate_last_sleep)
        rate_last_sleep_button.setOnClickListener {
            val intent = Intent(this@Home, Valuations::class.java)
            startActivity(intent)
        }

        val see_your_ratings_button = findViewById<Button>(R.id.see_your_ratings)
        see_your_ratings_button.setOnClickListener {
            //Redirigir a Activity donde se muestren todas las ratings
        }

        val toggle_Wake_Sleep = findViewById<Switch>(R.id.toggle_Wake_Sleep)
        val timePicker_sleep_act = findViewById<TimePicker>(R.id.timePicker_sleep)
        val timePicker_wake_act = findViewById<TimePicker>(R.id.timePicker_wake)
        toggle_Wake_Sleep.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                toggle_Wake_Sleep.setText("Wake")
                timePicker_wake_act.visibility = View.VISIBLE
                timePicker_sleep_act.visibility = View.GONE
            } else {
                toggle_Wake_Sleep.setText("Sleep")
                timePicker_sleep_act.visibility = View.VISIBLE
                timePicker_wake_act.visibility = View.GONE
            }
        }

    }
}
