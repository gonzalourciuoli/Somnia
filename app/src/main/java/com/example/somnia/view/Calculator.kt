package com.example.somnia.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.R
import com.example.somnia.controller.Controller

class Calculator : AppCompatActivity() {

    private lateinit var timeWakeUp: EditText
    private lateinit var hoursToSleep: EditText
    private lateinit var timeBed: EditText
    private val controller = Controller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        init()
    }

    private fun init() {
        timeWakeUp = findViewById(R.id.timeWakeUp)
        hoursToSleep = findViewById(R.id.hoursToSleep)
        timeBed = findViewById(R.id.timeBed)
        val cycleSwitch = findViewById<Button>(R.id.cycleSwitch) as Switch
        val cycleSwitch1 = findViewById<Button>(R.id.cycleSwitch1) as Switch

        cycleSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            val timeWakeUpText = timeWakeUp.text.toString()
            val hoursToSleepText = hoursToSleep.text.toString()
            val timeBedText = timeBed.text.toString()
            if (isChecked) {
                if (cycleSwitch1.isChecked) {
                    cycleSwitch1.isChecked = false
                }
                val result =
                    controller.calculateTimeToWakeUp(timeWakeUpText, hoursToSleepText, timeBedText)
                this.timeWakeUp.setText(result)

            }
        }

        cycleSwitch1.setOnCheckedChangeListener { buttonView, isChecked ->
            val timeWakeUpText = timeWakeUp.text.toString()
            val hoursToSleepText = hoursToSleep.text.toString()
            val timeBedText = timeBed.text.toString()
            if (isChecked) {
                if (cycleSwitch.isChecked) {
                    cycleSwitch.isChecked = false
                }
                val result =
                    controller.calculateTimeToGoBed(timeWakeUpText, hoursToSleepText, timeBedText)
                this.timeBed.setText(result)
            }
        }

        timeBed.setOnClickListener {
            cycleSwitch.isChecked = false
            cycleSwitch1.isChecked = false
        }
        timeWakeUp.setOnClickListener {
            cycleSwitch.isChecked = false
            cycleSwitch1.isChecked = false
        }
        hoursToSleep.setOnClickListener {
            cycleSwitch.isChecked = false
            cycleSwitch1.isChecked = false
        }
    }
}

