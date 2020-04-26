package com.example.somnia.model

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Alarm {
    private var title: String
    private var hour: String
    private var weekDays: MutableMap<String, Boolean>
    private var status: Boolean
    private var db: FirebaseFirestore
    private var auth: FirebaseAuth
    private lateinit var user: String


    constructor(title: String, hour: String, weekDays: MutableMap<String, Boolean>){
        this.title = title
        this.hour = hour
        this.weekDays = weekDays
        this.status = true
        this.user = ""
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

    }
    fun setStatus(newStatus:Boolean){
        this.status=newStatus
    }

    fun getStatus(): Boolean{
        return this.status
    }
    fun changeStatus(){
        if(status == true){
            this.status = false
        }
        else{
            this.status = true
        }
    }

    fun getTitle(): String{
        return this.title
    }

    fun getHour(): String{
        return this.hour
    }

    fun getWeekDays(): MutableMap<String, Boolean> {
        return this.weekDays
    }

    fun toMap(): MutableMap<String, Any> {
        val alarm_info = mutableMapOf(
            "Title" to title,
            "Hour" to hour,
            "Alarm on" to status,
            "User" to user
        )
        for(day in weekDays){
            alarm_info.put(day.key,day.value)
        }
        return alarm_info
    }

    fun setUser(user: String){
        this.user = user
    }

    override fun toString(): String{
        var alarma = ""
        alarma += (title + "\n")
        alarma += (hour + "\n")
        for (day in weekDays){
            alarma += (day.key + ": " + day.value.toString() + "\n")
        }
        alarma += status.toString()
        return alarma
    }

}