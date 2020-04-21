package com.example.somnia.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.R
import java.util.*
import kotlin.properties.Delegates

public class Calculator : AppCompatActivity() {

    private lateinit var timeWakeUp : EditText
    private lateinit var hoursToSleep : EditText
    private lateinit var  timeBed: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        timeWakeUp = findViewById(R.id.timeWakeUp)
        hoursToSleep = findViewById(R.id.hoursToSleep)
        timeBed = findViewById(R.id.timeBed)

        init()

    }

    private fun init(){
        val cycleSwitch = findViewById<Button>(R.id.cycleSwitch) as Switch
        val cycleSwitch1 = findViewById<Button>(R.id.cycleSwitch1) as Switch

        cycleSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                if (cycleSwitch1.isChecked){
                    cycleSwitch1.isChecked = false
                }
                this.calculateTimeToWakeUp()
            }
        }


        cycleSwitch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                if (cycleSwitch.isChecked){
                    cycleSwitch.isChecked = false
                }
                this.calculateTimeToGoBed()
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

    private fun calculateTimeToWakeUp(){
        var hoursBed = (this.timeBed.text.toString().substringBefore(":")).toInt()
        var minutsBed = (this.timeBed.text.toString().substringAfter(":")).toInt()
        var hours = (this.hoursToSleep.text.toString().substringBefore(":")).toInt()
        var minuts = (this.hoursToSleep.text.toString().substringAfter(":")).toInt()

        var resultHours = (hoursBed + hours)
        var resultMinuts = (minutsBed + minuts)
        if (resultHours >= 24){
            var i: Int
            i = resultHours - 24
            resultHours = i
        }
        if (resultMinuts >= 60){
            var i : Int
            i = resultMinuts - 60
            resultMinuts = i
            resultHours += 1
        }
        val result = resultHours.toString() + ":" + resultMinuts.toString()
        this.timeWakeUp.setText(result)
    }

    private fun calculateTimeToGoBed(){
        var hoursWakeUp = (this.timeWakeUp.text.toString().substringBefore(":")).toInt()
        var minutsWakeUp = (this.timeWakeUp.text.toString().substringAfter(":")).toInt()
        var hours = (this.hoursToSleep.text.toString().substringBefore(":")).toInt()
        var minuts = (this.hoursToSleep.text.toString().substringAfter(":")).toInt()

        var resultHours = (hoursWakeUp - hours)
        var resultMinuts = (minutsWakeUp - minuts)
        if (resultHours < 0){
            var i: Int
            i = resultHours + 24
            resultHours = i
        }
        if (resultMinuts < 0){
            var i : Int
            i = resultMinuts + 60
            resultMinuts = i
            resultHours -= 1
        }
        val result = resultHours.toString() + ":" + resultMinuts.toString()
        this.timeBed.setText(result)

    }
}