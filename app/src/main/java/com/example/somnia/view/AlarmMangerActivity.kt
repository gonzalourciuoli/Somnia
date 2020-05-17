package com.example.somnia.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.example.somnia.R
import com.example.somnia.controller.Controller
import kotlinx.android.synthetic.main.new_alarm.*

class AlarmMangerActivity : AppCompatActivity() {
    private lateinit var stopButton: Button
    private lateinit var alarmTitle: TextView
    private var controller = Controller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarm_manager)
        init()
    }

    private fun init(){
        stopButton =  findViewById(R.id.stopButton)
        alarmTitle =  findViewById(R.id.alarm)

        stopButton.setOnClickListener {
            controller.stopAlarm()
        }
    }

    }