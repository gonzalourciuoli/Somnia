package com.example.somnia.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.R
import com.example.somnia.controller.Controller

class Calculator() : AppCompatActivity() {

    private lateinit var timeWakeUp : EditText
    private lateinit var hoursToSleep : EditText
    private lateinit var  timeBed: EditText
    private val controller = Controller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        init()

    }

    private fun init(){
        timeWakeUp = findViewById(R.id.timeWakeUp)
        hoursToSleep = findViewById(R.id.hoursToSleep)
        timeBed = findViewById(R.id.timeBed)
        val cycleSwitch = findViewById<Button>(R.id.cycleSwitch) as Switch
        val cycleSwitch1 = findViewById<Button>(R.id.cycleSwitch1) as Switch

        val timeWakeUpText =  timeWakeUp.toString()
        val hoursToSleepText = hoursToSleep.toString()
        val timeBedText = timeBed.toString()
        cycleSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                if (cycleSwitch1.isChecked){
                    cycleSwitch1.isChecked = false
                }
                timeBed.setText(controller.calculateTimeToWakeUp(timeBedText, hoursToSleepText, timeWakeUpText))
            }
        }

        cycleSwitch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                if (cycleSwitch.isChecked){
                    cycleSwitch.isChecked = false
                }
                timeWakeUp.setText(controller.calculateTimeToGoBed(timeWakeUpText, hoursToSleepText, timeBedText))
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