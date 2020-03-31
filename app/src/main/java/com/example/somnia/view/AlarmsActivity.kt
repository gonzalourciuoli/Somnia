package com.example.somnia.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.somnia.R

class AlarmsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarms)
/*
val newAlarm_button = findViewById<Button>(R.id.add) as Button
        newAlarm_button.setOnClickListener {
            val intent = Intent(this@AlarmsActivity, New_alarmActivity::class.java)
            startActivity(intent)
        }
 */
    }
}
