package com.example.somnia.model

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class Manager : AppCompatActivity {
    private var am: AlarmManager
    private var cont: Context
    lateinit private var pi: PendingIntent
    private val alarm: Alarm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    constructor(alarm: Alarm){
        this.cont = this
        am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent: Intent = Intent(this,AlarmReceiver::class.java)
        this.alarm = alarm
    }

    fun setAlarm(){
        pi = PendingIntent.getBroadcast(this@Manager,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        am.setExact(AlarmManager.RTC_WAKEUP,alarm.getTimeLong(),pi)
        intent.putExtra("extra","on")
    }

    fun stopAlarm(){
        pi = PendingIntent.getBroadcast(this@Manager,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        am.cancel(pi)
        intent.putExtra("extra","off")
        sendBroadcast(intent)
    }
}