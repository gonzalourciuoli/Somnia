package com.example.somnia.model
import com.example.somnia.model.Alarm

class AlarmsList {
    private lateinit var alarmsList:MutableList<Alarm>

    constructor(alarmsList: MutableList<Alarm>){
        this.alarmsList = alarmsList
    }

    constructor(){

    }

    fun addAlarm(newAlarm:Alarm){
        alarmsList.add(newAlarm)
    }

    fun deleteAlarm(delAlarm: Alarm){
        val iterator = alarmsList.iterator()
        while(iterator.hasNext()){
            val alarm = iterator.next()
            if(alarm == delAlarm){
                iterator.remove()
            }
        }
    }

    fun getAlarmsList(): MutableList<Alarm>{
        return this.alarmsList
    }


}