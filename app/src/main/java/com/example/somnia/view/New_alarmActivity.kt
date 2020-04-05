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

        val monday_switch_act = findViewById<Switch>(R.id.monday_switch)
    }
}