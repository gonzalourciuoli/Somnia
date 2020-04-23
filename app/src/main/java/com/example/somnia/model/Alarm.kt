package com.example.somnia.model

import com.google.firebase.firestore.FirebaseFirestore

class Alarm {
    private var title: String
    private var hour: String
    private var weekDays: MutableMap<String, Boolean>
    private var status: Boolean


    constructor(title: String, hour: String, weekDays: MutableMap<String, Boolean>){
        this.title = title
        this.hour = hour
        this.weekDays = weekDays
        this.status = true

    }
    fun setStatus(newStatus:Boolean){
        this.status=newStatus
    }

    fun getStatus(): Boolean{
        return this.status
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

    fun toMap(): Map<String, Any?>{
        return mapOf(
            "Title" to title,
            "Hour" to hour,
            "Days of the week" to weekDays,
            "Alarm on" to status
        )
    }
}