package com.example.somnia.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var getResult: String? = intent!!.getStringExtra("on/off")
        var getState: String? = intent!!.getStringExtra("isRunning")
        if(getState == ""){
            getState = "no"
        }
        var alarmTitle: String? = intent!!.getStringExtra("alarmTitle")

        var service_intent: Intent = Intent(context, RingtoneService::class.java)
        service_intent.putExtra("alarmTitle",alarmTitle)
        service_intent.putExtra("on/off",getResult)
        service_intent.putExtra("isRunning",getState)
        context!!.startService(service_intent)
    }
}