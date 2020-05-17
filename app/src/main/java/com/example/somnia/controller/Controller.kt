package com.example.somnia.controller

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.somnia.model.*
import com.example.somnia.view.New_alarmActivity
import kotlinx.android.synthetic.main.activity_calendar.*

class Controller : AppCompatActivity{

    private val valuationsList : ValuationsList = ValuationsList()
    private var data_base: DataBase
    private lateinit var manager: Manager


    constructor(){
        data_base = DataBase()


    }
    fun calculateTimeToGoBed(timeWakeUp: String, hoursToSleep: String, timeBed: String): String {
        val calculator = Calculator(timeWakeUp, hoursToSleep, timeBed)
        return calculator.calculateTimeToGoBed()
    }
    fun calculateTimeToWakeUp(timeWakeUp: String, hoursToSleep: String, timeBed: String): String{
        val calculator = Calculator(timeWakeUp, hoursToSleep, timeBed)
        return calculator.calculateTimeToWakeUp()
    }


    fun addAlarm(title: String, hour: String, weekDays: MutableMap<String, Boolean>, user: String): Alarm{
        val newAlarm = Alarm(title, hour, weekDays, user)
        newAlarm.setUser(user)
        data_base.addAlarm(newAlarm)
       return newAlarm

    }

    fun addValuation(user: String, date: String, numStars: Float, sport_box: Boolean, coffee_box: Boolean,
                     alcohol_box: Boolean, valuation_comment: String){
        val newValuation = Valuation(user, date, numStars, sport_box, coffee_box, alcohol_box, valuation_comment)
        valuationsList.addValuation(newValuation)
        data_base.addValuation(newValuation, user, date)
    }

    fun getValuationString(user: String, id: String): String {
        val valuation = valuationsList.getValuationString(user, id)
        if (valuation == ""){
            return "No valuation on this day"
        }else{
            return valuation
        }
    }
    fun stopAlarm(){
        var stop = New_alarmActivity()
        stop.stopAlarm()
    }

    /*fun listViewValuations(user: String): MutableList<String> {
        return valuationsList.getList(user)
    }*/

    /*fun getAlarmsList(): MutableList<Alarm>{
        return data_base.getAlarmsList()
    }*/

    /*fun changeStatus(alarm: Alarm){
        data_base.changeStatus(alarm)
    }*/

    fun changePassword(password: String){
        data_base.changePassword(password)
    }

}