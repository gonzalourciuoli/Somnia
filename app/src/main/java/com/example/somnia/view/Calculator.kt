package com.example.somnia.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.R
import com.example.somnia.controller.Controller

public class Calculator : AppCompatActivity() {

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

        controller.calculator(cycleSwitch, cycleSwitch1, timeWakeUp, timeBed, hoursToSleep)
    }

}