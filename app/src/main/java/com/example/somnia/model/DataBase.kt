package com.example.somnia.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DataBase {
    private var db: FirebaseFirestore
    private var auth: FirebaseAuth


    constructor(){
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
    }

    fun addAlarm(alarm: Alarm){
        val user = auth.currentUser.toString()
        val alarm_map = alarm.toMap()
        db.collection("Alarms").document(user).set(alarm_map)
    }

    /*fun getAlarmsList(): MutableList<Alarm>{
        return
    }*/


}
