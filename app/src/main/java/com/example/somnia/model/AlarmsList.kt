package com.example.somnia.model
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class AlarmsList {
    private lateinit var alarmsList:MutableList<Alarm>
    private lateinit var db: FirebaseFirestore

    constructor(alarmsList: MutableList<Alarm>){
        this.alarmsList = alarmsList
        db = FirebaseFirestore.getInstance()

    }

    constructor(){
        alarmsList = mutableListOf<Alarm>()
        db = FirebaseFirestore.getInstance()

    }

    fun addAlarm(newAlarm:Alarm){
        alarmsList.add(newAlarm)
        val infoAlarm: Map<String, Any?> = newAlarm.toMap()
        db.collection("Alarms")
            .add(infoAlarm)
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