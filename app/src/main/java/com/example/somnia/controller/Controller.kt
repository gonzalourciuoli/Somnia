package com.example.somnia.controller

import com.example.somnia.model.*

class Controller{

    val valuationsList : ValuationsList = ValuationsList()
    private var data_base: DataBase

    constructor(){
        data_base = DataBase()
    }
    fun calculateTimeToGoBed(timeWakeUp: String, hoursToSleep: String, timeBed: String): String {
        val calculator = Calculator(timeWakeUp, hoursToSleep, timeBed)
        return calculator.calculateTimeToGoBed()
    }
    fun calculateTimeToWakeUp(timeBed: String, hoursToSleep: String, timeWakeUp: String): String{
        val calculator = Calculator(timeWakeUp, hoursToSleep, timeBed)
        return calculator.calculateTimeToWakeUp()
    }


    fun addAlarm(title: String, hour: String, weekDays: MutableMap<String, Boolean>){
        val newAlarm: Alarm = Alarm(title, hour, weekDays)
        data_base.addAlarm(newAlarm)
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

    fun listViewValuations(user: String): MutableList<String> {
        return valuationsList.getList(user)
    }

    fun getAlarmsList(): MutableList<Alarm>{
        return data_base.getAlarmsList()
    }

    /*fun changeStatus(alarm: Alarm){
        data_base.changeStatus(alarm)
    }*/

    fun changePassword(password: String){
        data_base.changePassword(password)
    }

}