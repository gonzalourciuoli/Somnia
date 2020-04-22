package com.example.somnia.view
import com.example.somnia.R
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.alarm_design.*


class New_alarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_alarm)

        val monday_switch = findViewById<Switch>(R.id.monday_switch)
        val tuesday_switch = findViewById<Switch>(R.id.tuesday_switch)
        val wednesday_switch = findViewById<Switch>(R.id.wednesday_switch)
        val thursday_switch = findViewById<Switch>(R.id.thursday_switch)
        val friday_switch = findViewById<Switch>(R.id.friday_switch)
        val saturday_switch = findViewById<Switch>(R.id.saturday_switch)
        val sunday_switch = findViewById<Switch>(R.id.sunday_switch)

    }
}