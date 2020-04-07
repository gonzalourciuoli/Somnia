package com.example.somnia.model

class Alarm {
    var alarmHour:String
    var alarmDays:Array<String>
    var activatedAlarm:Boolean

    constructor(alarmHour:String, alarmDays:Array<String>){
        this.alarmHour=alarmHour
        this.alarmDays=alarmDays
        this.activatedAlarm=true
    }
    fun changeStatus(newStatus:Boolean){
        this.activatedAlarm=newStatus
    }
}