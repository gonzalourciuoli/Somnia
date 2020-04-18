package com.example.somnia.controller

import com.example.somnia.model.Alarm
import com.example.somnia.model.AlarmsList

class Controller {
    val alarmsList: AlarmsList = AlarmsList()
    public fun addAlarm(title: String, hour: String, weekDays: MutableMap<String, Boolean>){
        val newAlarm: Alarm = Alarm(title, hour, weekDays)
        alarmsList.addAlarm(newAlarm)
    }
}