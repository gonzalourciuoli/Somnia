package com.example.somnia.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.somnia.AlarmsActivity
import com.example.somnia.New_alarmActivity
import com.example.somnia.R

class Info_alarmActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_alarm)

        val accept_button = findViewById<Button>(R.id.accept) as Button
        accept_button.setOnClickListener {
            val intent = Intent(this@Info_alarmActivity, AlarmsActivity::class.java)
            startActivity(intent)
        }


        val edit_button = findViewById<Button>(R.id.edit) as Button
        edit_button.setOnClickListener {
            val intent = Intent(this@Info_alarmActivity, New_alarmActivity::class.java)
            startActivity(intent)
        }
    }
}