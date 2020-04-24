package com.example.somnia.controller

import android.content.Context
import android.content.SharedPreferences
import android.widget.EditText
import android.widget.Switch
import com.example.somnia.model.Alarm
import com.example.somnia.model.DataBase
import com.example.somnia.model.AlarmsList
import com.example.somnia.model.Valuation
import com.example.somnia.model.ValuationsList
import com.example.somnia.view.Valuations
import kotlin.math.*

class Controller{
    val alarmsList: AlarmsList = AlarmsList()
    val valuationsList : ValuationsList = ValuationsList()

    private var data_base: DataBase

    constructor(){
        data_base = DataBase()
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


    public fun addAlarm(title: String, hour: String, weekDays: MutableMap<String, Boolean>){
        val newAlarm: Alarm = Alarm(title, hour, weekDays)
        data_base.addAlarm(newAlarm)
    }

    fun addValuation(user: String, date: String, numStars: Float, sport_box: Boolean, coffee_box: Boolean,
                     alcohol_box: Boolean, valuation_comment: String){
        val newValuation = Valuation(user, date, numStars, sport_box, coffee_box, alcohol_box, valuation_comment)
        valuationsList.addValuation(newValuation)

    }

    fun getValuationString(user: String, id: String): String {
        val valuation = valuationsList.getValuationString(user, id)
        if (valuation == ""){
            return "No valuation on this day"
        }else{
            return valuation
        }
    }

    fun listViewValuations(user: String): MutableList<String>{
        return valuationsList.getList(user)
    }

    /*fun getAlarmsList(): MutableList<Alarm>{
        return data_base.getAlarmsList()
    }*/
}