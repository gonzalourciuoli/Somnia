package com.example.somnia.controller

import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.view.Valuations
import kotlin.math.*

class Controller{

    fun controller(){

    }

    fun calculator(cycleSwitch: Switch, cycleSwitch1: Switch, timeWakeUp: EditText, timeBed: EditText, hoursToSleep: EditText) {
        cycleSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                if (cycleSwitch1.isChecked){
                    cycleSwitch1.isChecked = false
                }
                this.calculateTimeToWakeUp(timeBed, hoursToSleep, timeWakeUp)
            }
        }


        cycleSwitch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                if (cycleSwitch.isChecked){
                    cycleSwitch.isChecked = false
                }
                this.calculateTimeToGoBed(timeWakeUp, hoursToSleep, timeBed)
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

    private fun calculateTimeToGoBed(timeWakeUp: EditText, hoursToSleep: EditText, timeBed: EditText) {
        var hoursWakeUp = (timeWakeUp.text.toString().substringBefore(":")).toInt()
        var minutsWakeUp = (timeWakeUp.text.toString().substringAfter(":")).toInt()
        var hours = (hoursToSleep.text.toString().substringBefore(":")).toInt()
        var minuts = (hoursToSleep.text.toString().substringAfter(":")).toInt()

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
        timeBed.setText(result)
    }


    private fun calculateTimeToWakeUp(timeBed: EditText, hoursToSleep: EditText, timeWakeUp: EditText){
        var hoursBed = (timeBed.text.toString().substringBefore(":")).toInt()
        var minutsBed = (timeBed.text.toString().substringAfter(":")).toInt()
        var hours = (hoursToSleep.text.toString().substringBefore(":")).toInt()
        var minuts = (hoursToSleep.text.toString().substringAfter(":")).toInt()

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
        timeWakeUp.setText(result)
    }



}